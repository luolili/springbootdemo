package com.example.springwebfluxwaiterdemo.luo.service;

import com.example.springwebfluxwaiterdemo.luo.model.Coffee;
import com.example.springwebfluxwaiterdemo.luo.repo.CoffeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepo coffeeRepo;

    public Mono<Coffee> findById(Long id) {
        return coffeeRepo.findById(id);
    }

    public Mono<Coffee> findByName(String name) {
        return coffeeRepo.findByName(name);

    }

    public Flux<Coffee> findAll() {
        return coffeeRepo.findAll();
    }
}
