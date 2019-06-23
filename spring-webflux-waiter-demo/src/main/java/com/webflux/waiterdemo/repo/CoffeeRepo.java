package com.webflux.waiterdemo.repo;

import com.webflux.waiterdemo.model.Coffee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import reactor.core.publisher.Mono;

public interface CoffeeRepo extends R2dbcRepository<Coffee, Long> {

    @Query("select * from t_coffee where name=$1")
//from r2db query ,like jpa
    Mono<Coffee> findByName(String name);
}
