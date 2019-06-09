package springbucks.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springbucks.model.Coffee;
import springbucks.model.CoffeeOrder;
import springbucks.service.CoffeeOrderService;
import springbucks.service.CoffeeService;

import javax.validation.Valid;
import java.util.List;

/**
 * request handle
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {

    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/{id}")
    @ResponseBody
    public CoffeeOrder getOrder(@PathVariable("id") Long id) {
        return orderService.get(id);
    }

    //simple version
    //@PostMapping("/")
    //complex version: return json and utf-8, use json
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }

    @ModelAttribute//put cooffeList into modelMap
    public List<Coffee> coffeeList() {
        return coffeeService.getAllCoffee();
    }

    @GetMapping(path = "/")
    public ModelAndView showCreateForm() {
        return new ModelAndView("create-order-form");
    }

    //use form
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    //@ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@Valid NewOrderRequest newOrder, BindingResult result
            , ModelMap map) {
        if (result.hasErrors()) {
            log.warn("Binding Result: {}", result);
            map.addAttribute("message", result.toString());
            return "create-order-form";
        }

        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[]{});
        CoffeeOrder order = orderService.createOrder(newOrder.getCustomer(), coffeeList);
        return "redirect:/order/" + order.getId();
    }
}
