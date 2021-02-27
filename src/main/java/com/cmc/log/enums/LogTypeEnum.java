package com.cmc.log.enums;

import lombok.Data;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2021/2/25 下午10:36
 */
public enum LogTypeEnum {
    ADD(1),
    EDIT(2),
    DELETE(3);

    private int code;

    LogTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
