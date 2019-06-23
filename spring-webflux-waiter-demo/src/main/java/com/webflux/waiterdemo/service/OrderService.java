package com.webflux.waiterdemo.service;

import com.webflux.waiterdemo.model.Coffee;
import com.webflux.waiterdemo.model.CoffeeOrder;
import com.webflux.waiterdemo.model.OrderState;
import com.webflux.waiterdemo.repo.CoffeeOrderRepo;
import com.webflux.waiterdemo.repo.CoffeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private CoffeeOrderRepo orderRepo;

    @Autowired
    private CoffeeRepo coffeeRepo;

    public Mono<CoffeeOrder> getById(Long id) {
        return orderRepo.get(id);
    }

    public Mono<Long> create(String customer, List<String> items) {
        return Flux.fromStream(items.stream())
                .flatMap(n -> coffeeRepo.findByName(n))
                .collectList()
                .flatMap(l ->
                        orderRepo.save(CoffeeOrder.builder()
                                .customer(customer)
                                .items(l)
                                .state(OrderState.INIT)
                                .createTime(new Date())
                                .updateTime(new Date())
                                .build())
                );
    }


}
