package com.cmc.log.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/27 下午8:32
 */
@Data
@TableName("car")
public class CarEntity {
    @TableId
    private Long id;

    @TableField("car_name")
    private String carName;

    @TableField("cat_price")
    private BigDecimal carPrice;
}
