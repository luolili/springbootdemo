package springbucks.jpademo.repository;

import springbucks.jpademo.model.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository02 extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_Name(String name);
}
