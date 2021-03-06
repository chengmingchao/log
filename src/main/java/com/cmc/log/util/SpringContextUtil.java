package com.cmc.log.util;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午9:55
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext=applicationContext;
    }

    public static <T> T getBean(String name, Class<T> clazz){
        return applicationContext.getBean(name,clazz);
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
}
