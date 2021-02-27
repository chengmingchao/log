package com.cmc.log.aop;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmc.log.annotation.ServiceLog;
import com.cmc.log.dao.LogDao;
import com.cmc.log.entity.LogEntity;
import com.cmc.log.enums.LogTypeEnum;
import com.cmc.log.util.LogThreadLocal;
import com.cmc.log.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

    @Around("@annotation(com.cmc.log.annotation.ServiceLog)")
    public void around(ProceedingJoinPoint joinPoint) {
        log.error("切面开始");
        //获取修改前的数据
        Object beJson = new Object();


        //拦截的放参数类型
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法！");
        }
        //获取方法签名
        MethodSignature methodSignature = (MethodSignature) signature;

        //获取方法
        Method method = methodSignature.getMethod();
        //获取方法上的注解
        ServiceLog serviceLog = method.getAnnotation(ServiceLog.class);
        //获取业务类型
        int serviceType = serviceLog.serviceType();
        //使用到的mapper
        String mapper = serviceLog.mapper();
        //定义参数类型
        Class classType = serviceLog.classType();
        //日志类型
        int[] logType = serviceLog.logType();
        List<Integer> logTypes = new ArrayList<>();
        for (int i : logType) {
            logTypes.add(i);
        }
        //获取注解的入参
        Object[] args = joinPoint.getArgs();
        //入参
        String afString = null;
        if (args.length != 0 && args != null) {
            for (Object arg : args) {
                if (arg.getClass().getName().equals(classType.getName())) {
                    afString = JSONObject.toJSONString(arg);
                    break;
                } else {
                    throw new IllegalArgumentException("参数中找不到与定义的参数相匹配的类型");
                }
            }
        }

        //日志实体类
        LogEntity logEntity = new LogEntity();
        logEntity.setServiceType(serviceType);
        // 操作人 从session中获取
        //TODO
        logEntity.setOperator("cmc");

        //获取mapper对象
        BaseMapper bean = SpringContextUtil.getBean(mapper, BaseMapper.class);

        JSONObject afJsonObject = JSONObject.parseObject(afString);
        Long id = ((Integer) afJsonObject.get("id")).longValue();

        //修改
        if (logTypes.contains(2)){
            logEntity.setLogType(LogTypeEnum.EDIT.getCode());
            //获取修改前的日志
            Object o = bean.selectById(id);
            logEntity.setServiceId(id);
            logEntity.setBeJson(JSONObject.toJSONString(o));
        }
        //删除
        if (logTypes.contains(2)){
            //TODO
        }
        logEntity.setAfJson(afString);
        try {
            //执行方法
            Object proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //新增
        if (logTypes.contains(1)){
            Integer code = LogTypeEnum.ADD.getCode();
            log.info("code:" + code);
            logEntity.setServiceId(LogThreadLocal.getThrealLocal());
            logEntity.setLogType(LogTypeEnum.ADD.getCode());
            logEntity.setBeJson(JSONObject.toJSONString(beJson));
        }
        //TODO 防止内存泄漏
        //LogThreadLocal.remove();
        //保存日志
        logDao.insert(logEntity);
        log.error("切面结束");
    }
}
