package com.cmc.log.annotation;

import java.lang.annotation.*;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午9:15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
@Inherited
public @interface LogService {

    int serviceType();  //业务类型

    String mapper(); //使用到的mapper

}
