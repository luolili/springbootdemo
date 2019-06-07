package springbucks.jpademo.repository;

import org.springframework.data.repository.CrudRepository;
import springbucks.jpademo.model.Coffee;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {

}
