package springbucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbucks.model.CoffeeOrder;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
