package com.cmc.log.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/27 下午9:32
 */
@Data
public class UserCar {

    private Long id;

    private String name;

    private Integer age;

    private String address;

    private String job;

    private String carName;

    private BigDecimal carPrice;
}
