package springmvcdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springmvcdemo.model.Coffee;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    List<Coffee> findByNameInOrderById(List<String> list);
}
