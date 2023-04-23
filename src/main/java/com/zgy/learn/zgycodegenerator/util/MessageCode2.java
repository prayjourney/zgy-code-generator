package com.zgy.learn.zgycodegenerator.util;

import lombok.Getter;

@Getter
public enum MessageCode2 {
    SUCCESS(0, "success"), FAIL(1001, "fail");
    private Integer code;
    private String message;

    private MessageCode2(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessageByCode(Integer code) {
        MessageCode2[] values = MessageCode2.values();
        for (MessageCode2 val : values) {
            if (val.getCode().equals(code)) {
                return val.getMessage();
            }
        }
        return "";
    }

}
