package springbucks.jpademo.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * entity: coffee order
 */
@Entity
@Table(name = "t_order")

@ToString(callSuper = true)

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder extends BaseEntity {

    private String customer;

    @ManyToMany
    @JoinTable(name = "t_order_coffee")
    private List<Coffee> items;

    @Column(nullable = false)
    private Integer state;

}
