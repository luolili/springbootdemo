package com.example.firstdemo.repository;

import com.example.firstdemo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User}
 */
@Repository
public class UserRepository {

    private final ConcurrentHashMap<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger idGenerator =
            new AtomicInteger();

    /**
     * memory-way to save it:Map
     *
     * @param user
     * @return
     */
    public boolean save(User user) {
        boolean success = false;
        Integer id = idGenerator.incrementAndGet();

        user.setId(id);
        repository.put(id, user);
        return repository.put(id, user) == null;
    }

    public Collection<User> findAll() {

        Collection<User> users = repository.values();
        return users;
    }
}
