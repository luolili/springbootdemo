package com.webflux.waiterdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "t_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder implements Serializable {

    @Id
    private Long id;
    private String customer;
    private OrderState state;
    private List<Coffee> items;
    private Date createTime;
    private Date updateTime;

}
