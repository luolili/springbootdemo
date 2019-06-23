package com.webflux.waiterdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_coffee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements Serializable {

    @Id
    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;

}
