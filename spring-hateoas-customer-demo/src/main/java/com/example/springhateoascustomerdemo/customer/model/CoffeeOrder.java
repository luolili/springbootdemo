package com.example.springhateoascustomerdemo.customer.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder extends BaseEntity implements Serializable {
    private String customer;
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")//from javax
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;

}
