package aspectdemo;

import aspectdemo.model.Coffee;
import aspectdemo.model.CoffeeOrder;
import aspectdemo.model.OrderState;
import aspectdemo.repository.CoffeeRepository;
import aspectdemo.service.CoffeeOrderService;
import aspectdemo.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories
public class SpringAspectDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringAspectDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("all coffee: {}", coffeeRepository.findAll());
        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");


        if (latte.isPresent()) {
            CoffeeOrder order = orderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID: {}", orderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", orderService.updateState(order, OrderState.INIT));
        }
    }
}
