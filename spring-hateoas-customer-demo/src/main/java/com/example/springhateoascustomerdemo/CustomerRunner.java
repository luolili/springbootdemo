package com.example.springhateoascustomerdemo;

import com.example.springhateoascustomerdemo.customer.model.Coffee;
import com.example.springhateoascustomerdemo.customer.model.CoffeeOrder;
import com.example.springhateoascustomerdemo.customer.model.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@Slf4j
public class CustomerRunner implements ApplicationRunner {

    private static final URI ROOT_URI = URI.create("http://localhost:8080/");

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Link coffeeLink = getLink(ROOT_URI, "coffees");
        readCoffeeMenu(coffeeLink);

        Resource<Coffee> americano  = addCoffee(coffeeLink);
        //order
        Link orderLink = getLink(ROOT_URI, "coffeeOrders");
        addOrder(orderLink, americano);
        queryOrders(orderLink);



    }

    public Link getLink(URI uri, String rel) {

        ResponseEntity<Resources<Link>> rootResp = restTemplate.exchange(uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Link>>() {
                });

        Link link = rootResp.getBody().getLink(rel);
        log.info("Link:{}", link);
        return link;
    }


    public void readCoffeeMenu(Link link) {
        ResponseEntity<Resources<Link>> coffeeResp = restTemplate.exchange(link.getTemplate().expand(),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<Link>>() {
                });

        log.info("coffee Resp:{}",coffeeResp.getBody());
    }

    public Resource<Coffee> addCoffee(Link link) {
        //-1 create a coffee
        Coffee americano = Coffee.builder()
                .name("americano")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0))
                .build();
        //-2 create req
        RequestEntity<Coffee> req =
                RequestEntity.post(link.getTemplate().expand()).body(americano);
        //-3 get resp
        ResponseEntity<Resource<Coffee>> resp =
                restTemplate.exchange(req,
                        new ParameterizedTypeReference<Resource<Coffee>>() {});
        log.info("add Coffee Response: {}", resp);
        //-4 get resp body
        return resp.getBody();

    }

    private void addOrder(Link link, Resource<Coffee> coffee) {
        //-1 create an order
        CoffeeOrder newOrder = CoffeeOrder.builder()
                .customer("Li Lei")
                .state(OrderState.INIT)
                .build();
        //-2 create req
        RequestEntity<?> req =
                RequestEntity.post(link.getTemplate().expand()).body(newOrder);
        //-3 get resp
        ResponseEntity<Resource<CoffeeOrder>> resp =
                restTemplate.exchange(req,
                        new ParameterizedTypeReference<Resource<CoffeeOrder>>() {});
        log.info("add Order Response: {}", resp);

        //-4 get order body
        Resource<CoffeeOrder> order = resp.getBody();
        //-5 get order link
        Link items = order.getLink("items");

        req = RequestEntity.post(items.getTemplate().expand()).body(coffee.getLink("self"));
        ResponseEntity<String> itemResp = restTemplate.exchange(req, String.class);
        log.info("add Order Items Response: {}", itemResp);
    }

    private void queryOrders(Link link) {
        ResponseEntity<String> resp = restTemplate.getForEntity(link.getTemplate().expand(), String.class);
        log.info("query Order Response: {}", resp);
    }
}
