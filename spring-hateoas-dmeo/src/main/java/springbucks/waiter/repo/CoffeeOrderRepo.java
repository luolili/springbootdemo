package springbucks.waiter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import springbucks.waiter.model.CoffeeOrder;
//@RepositoryRestResource(path = "/order")
public interface CoffeeOrderRepo extends JpaRepository<CoffeeOrder,Long> {
}
