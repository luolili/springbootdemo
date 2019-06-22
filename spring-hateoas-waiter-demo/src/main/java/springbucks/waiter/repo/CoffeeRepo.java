package springbucks.waiter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import springbucks.waiter.model.Coffee;
import springbucks.waiter.model.CoffeeOrder;

import java.util.List;

@RepositoryRestResource(path = "/coffee")
public interface CoffeeRepo extends JpaRepository<Coffee,Long> {

    List<Coffee> findByNameInOrderById(List<String> list);
    Coffee findByName(String name);
}
