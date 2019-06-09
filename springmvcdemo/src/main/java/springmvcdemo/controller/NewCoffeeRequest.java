package springmvcdemo.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class NewCoffeeRequest {

    @NotEmpty//javax
    private String name;

    @NotNull//javax
    private Money price;
}
