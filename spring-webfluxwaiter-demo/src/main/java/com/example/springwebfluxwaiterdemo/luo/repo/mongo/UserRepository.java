package com.example.springwebfluxwaiterdemo.luo.repo.mongo;

import com.example.springwebfluxwaiterdemo.luo.model.mongo.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
