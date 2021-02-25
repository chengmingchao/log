package com.cmc.log.aop;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmc.log.annotation.LogService;
import com.cmc.log.dao.LogDao;
import com.cmc.log.dao.UserDao;
import com.cmc.log.entity.LogEntity;
import com.cmc.log.enums.LogTypeEnum;
import com.cmc.log.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午9:37
 * 切面类
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private LogDao logDao;

    @Around("@annotation(com.cmc.log.annotation.LogService)")
    public void around(ProceedingJoinPoint joinPoint){
        log.error("切面开始");
        //获取修改前的数据
        Object beJson=new Object();
        //获取注解的入参
        Object[] args = joinPoint.getArgs();
        Object afJson = args[0];
        Object operator = args[1];
        //获取方法签名
        MethodSignature methodSignature= (MethodSignature)joinPoint.getSignature();
        //获取方法
        Method method = methodSignature.getMethod();
        //获取方法上的注解
        LogService logService = method.getAnnotation(LogService.class);
        //获取业务类型
        int serviceType = logService.serviceType();
        //使用到的mapper
        String mapper = logService.mapper();

        //日志实体类
        LogEntity logEntity=new LogEntity();
        logEntity.setServiceType(serviceType);
        logEntity.setOperator(operator.toString());

        //获取mapper对象
        BaseMapper bean = SpringContextUtil.getBean(mapper, BaseMapper.class);



        String s = JSONObject.toJSONString(afJson);

        JSONObject afJsonObject = JSONObject.parseObject(s);
        Long id = ((Integer) afJsonObject.get("id")).longValue();
        //新增
        if (id==null){
            Integer code = LogTypeEnum.ADD.getCode();
            log.info("code:"+code);
            logEntity.setLogType(LogTypeEnum.ADD.getCode());
            logEntity.setBeJson(JSONObject.toJSONString(beJson));
        }
        //修改
        else {
            logEntity.setLogType(LogTypeEnum.EDIT.getCode());
            //获取修改前的日志
            Object o = bean.selectById(id);
            logEntity.setBeJson(JSONObject.toJSONString(o));
        }
        logEntity.setAfJson(JSONObject.toJSONString(afJson));
        try {
            //执行方法
            Object proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        //保存日志
        logDao.insert(logEntity);
        log.error("切面结束");
    }
}
