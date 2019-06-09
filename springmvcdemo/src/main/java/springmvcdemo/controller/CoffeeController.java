package springmvcdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springmvcdemo.model.Coffee;
import springmvcdemo.service.CoffeeService;

import java.util.List;

@Controller
@RequestMapping("/coffee")
@Slf4j
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    //@GetMapping("/")
    //when there is no param called name
    @GetMapping(path = "/", params = "!name")
    @ResponseBody
    public List<Coffee> getAll() {

        return coffeeService.getAllCoffee();
    }

    //simple version
    //@GetMapping("/")
    //there must be a request param called name
    @GetMapping(path = "/", params = "name")
    @ResponseBody
    public Coffee getByName(@RequestParam String name) {

        return coffeeService.getCoffee(name);
    }


}
