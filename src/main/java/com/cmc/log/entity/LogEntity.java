package com.cmc.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.annotation.Inherited;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午9:06
 */
@Data
@TableName("log")
public class LogEntity {
    @TableId
    private Long id;

    @TableField("be_json")
    private String beJson;

    @TableField("af_json")
    private String afJson;

    @TableField("service_id")
    private Long serviceId;

    @TableField("service_type")
    private Integer serviceType;

    @TableField("log_type")
    private Integer logType;

    @TableField("operator")
    private String operator;
}
