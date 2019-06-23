package com.webflux.waiterdemo.service;

import com.webflux.waiterdemo.model.Coffee;
import com.webflux.waiterdemo.repo.CoffeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepo coffeeRepo;

    public Mono<Coffee> getById(Long id) {
        return coffeeRepo.findById(id);
    }

    public Mono<Coffee> findByName(String name) {
        return coffeeRepo.findByName(name);

    }

    public Flux<Coffee> findAll() {
        return coffeeRepo.findAll();
    }
}
