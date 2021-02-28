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
public @interface ServiceLog {

    int serviceType();  //业务类型

    String mapper(); //使用到的mapper

    String queryMethod() default ""; //查询单个详情的bean的方法

    Class classType();  //业务参数类型

    int[] logType();//日志类型  1：新增、2：修改、3：删除
}
