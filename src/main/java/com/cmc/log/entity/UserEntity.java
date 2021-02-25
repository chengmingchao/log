package com.cmc.log.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午9:11
 */
@Data
@TableName("user")
public class UserEntity {
    @TableId
    private Long id;
    @TableField("name")
    private String name;
    @TableField("age")
    private Integer age;
    @TableField("address")
    private String address;
    @TableField("job")
    private String job;
}
