package springmvcdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springmvcdemo.model.CoffeeOrder;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
