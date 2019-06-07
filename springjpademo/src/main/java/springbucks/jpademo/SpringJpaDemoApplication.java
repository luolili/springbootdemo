package springbucks.jpademo;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import springbucks.jpademo.model.Coffee;
import springbucks.jpademo.model.CoffeeOrder;
import springbucks.jpademo.repository.CoffeeOrderRepository02;
import springbucks.jpademo.repository.CoffeeRepository02;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
//open jpa
@EnableJpaRepositories
//open tx
@EnableTransactionManagement
public class SpringJpaDemoApplication implements ApplicationRunner {


   /* @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;*/

    @Autowired
    private CoffeeRepository02 coffeeRepository02;

    @Autowired
    private CoffeeOrderRepository02 orderRepository02;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaDemoApplication.class, args);
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {

        initOrders();
        findOrders();
    }

    private void initOrders() {

        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();

        coffeeRepository02.save(latte);

        log.info("Coffee: {}", latte);

        Coffee espresso = Coffee.builder().name("espresso ")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();

        coffeeRepository02.save(espresso);

        log.info("Coffee: {}", espresso);

        CoffeeOrder order = CoffeeOrder.builder()
                .customer("li bi")
                .items(Collections.singletonList(espresso))
                .state(0)
                .build();

        orderRepository02.save(order);

        log.info("Order: {}", order);
    }


    public void findOrders() {
        coffeeRepository02.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(c -> {
                    log.info("loading: {}", c);
                });

        List<CoffeeOrder> list = orderRepository02.findTop3ByOrderByUpdateTimeDescIdAsc();

        log.info("findTop3ByOrderByUpdateTimeDescIdAsc:{}", getJoinedOrderId(list));

        list = orderRepository02.findByCustomerOrderById("li bi");
        log.info("findByCustomerOrderById:{}", getJoinedOrderId(list));
        list.forEach(o -> {
            log.info("Order {}", o.getId());
            o.getItems().forEach(i -> log.info("  Item {}", i));
        });

        list = orderRepository02.findByItems_Name("latte");
        log.info("findByItems_Name: {}", getJoinedOrderId(list));

    }

    private String getJoinedOrderId(List<CoffeeOrder> list) {
        return list.stream().map(o -> o.getId().toString())
                .collect(Collectors.joining(","));
    }


}
