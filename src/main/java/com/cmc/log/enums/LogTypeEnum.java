package com.cmc.log.enums;

import lombok.Data;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午10:36
 */
public enum LogTypeEnum {
    ADD(1,"ADD"),
    EDIT(2,"EDIT");

    private Integer code;
    private String logType;

    LogTypeEnum(Integer i, String add) {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
}
