package com.example.springwebclientdemo;

import com.example.springwebclientdemo.api.IUserApi;
import com.example.springwebclientdemo.model.Coffee;
import com.example.springwebclientdemo.proxy.JDKProxyCreator;
import com.example.springwebclientdemo.proxy.ProxyCreator;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

/**
 * reactive to access web resource
 */

@SpringBootApplication
@Slf4j
public class WebclientDemoApplication implements ApplicationRunner {
    @Autowired
    private WebClient webClient;

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebclientDemoApplication.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8080").build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(2);

        webClient.get()
                .uri("/coffee/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(Coffee.class)
                .doOnError(t -> log.error("Error: ", t))
                .doFinally(s -> cdl.countDown())
                .subscribeOn(Schedulers.single())
                .subscribe(c -> log.info("Coffee 1: {}", c));

        Mono<Coffee> americano = Mono.just(
                Coffee.builder()
                        .name("americano")
                        .price(Money.of(CurrencyUnit.of("CNY"), 25.00))
                        .build()
        );
        webClient.post()
                .uri("/coffee/")
                .body(americano, Coffee.class)
                .retrieve()
                .bodyToMono(Coffee.class)
                .doFinally(s -> cdl.countDown())
                .subscribeOn(Schedulers.single())
                .subscribe(c -> log.info("Coffee Created: {}", c));

        cdl.await();

        webClient.get()
                .uri("/coffee/")
                .retrieve()
                .bodyToFlux(Coffee.class)
                .toStream()
                .forEach(c -> log.info("Coffee in List: {}", c));
    }

    //-------
    @Bean
    ProxyCreator jdkProxyCreator() {
        return new JDKProxyCreator();
    }

    @Bean
    FactoryBean<IUserApi> userApiFactoryBean(ProxyCreator proxyCreator) {
        return new FactoryBean<IUserApi>() {
            //返回代理对象
            @Override
            public IUserApi getObject() throws Exception {
                return (IUserApi) proxyCreator.createProxy(this.getObjectType());
            }

            @Override
            public Class<?> getObjectType() {
                return IUserApi.class;
            }
        };
    }
}