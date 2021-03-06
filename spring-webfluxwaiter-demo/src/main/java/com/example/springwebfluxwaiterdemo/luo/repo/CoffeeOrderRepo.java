package com.example.springwebfluxwaiterdemo.luo.repo;

import com.example.springwebfluxwaiterdemo.luo.model.Coffee;
import com.example.springwebfluxwaiterdemo.luo.model.CoffeeOrder;
import com.example.springwebfluxwaiterdemo.luo.model.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class CoffeeOrderRepo {

    @Autowired
    private DatabaseClient databaseClient;//from r2db

    public Mono<CoffeeOrder> get(Long id) {
        return databaseClient.execute()
                .sql("select * from t_order where id = " + id)
                .map((r, rm) ->
                        CoffeeOrder.builder()
                                .id(id)
                                .customer(r.get("customer", String.class))
                                .state(OrderState.values()[r.get("state", Integer.class)])
                                .createTime(r.get("create_time", Date.class))
                                .updateTime(r.get("update_time", Date.class))
                                .items(new ArrayList<Coffee>())
                                .build()
                )
                .first()
                .flatMap(
                        o ->
                                databaseClient.execute()
                                        .sql("select c.* from t_coffee c, t_order oc" +
                                                "where c.id = oc.items_id and oc.coffee_order_id = " + id)
                                        .as(Coffee.class)
                                        .fetch()
                                        .all()
                                        .collectList()
                                        .flatMap(l ->
                                        {
                                            o.getItems().addAll(l);
                                            return Mono.just(o);
                                        })
                );

    }

    public Mono<Long> save(CoffeeOrder order) {
        return databaseClient.insert().into("t_order")
                // set value for property
                .value("customer", order.getCustomer())
                .value("state", order.getState().ordinal())
                .value("create_time", new Timestamp(order.getCreateTime().getTime()))
                .value("update_time", new Timestamp(order.getUpdateTime().getTime()))
                .fetch()
                .first()
                .flatMap(m -> Mono.just((Long) m.get("ID")))
                .flatMap(id -> Flux.fromIterable(order.getItems())
                        .flatMap(c -> databaseClient.insert().into("t_order_coffee")
                                .value("coffee_order_id", id)
                                .value("items_id", c.getId())
                                .then()).then(Mono.just(id)));
    }
}
