package com.cmc.log.util;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/27 下午9:59
 */
public class LogThreadLocal {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void setThreadLocal(Long value){
        threadLocal.set(value);
    }

    public static Long getThrealLocal(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
