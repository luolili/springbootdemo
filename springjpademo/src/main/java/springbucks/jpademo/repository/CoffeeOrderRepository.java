package springbucks.jpademo.repository;

import org.springframework.data.repository.CrudRepository;
import springbucks.jpademo.model.CoffeeOrder;

import java.util.List;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_Name(String name);
}
