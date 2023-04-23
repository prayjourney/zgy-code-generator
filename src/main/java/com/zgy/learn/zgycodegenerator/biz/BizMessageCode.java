package com.zgy.learn.zgycodegenerator.biz;

import lombok.Getter;

/**
 * 统一的错误码
 */
@Getter
public enum BizMessageCode {
    SUCCESS(0, "success"), FAIL(1001, "fail");
    private Integer code;
    private String message;

    private BizMessageCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessageByCode(Integer code) {
        BizMessageCode[] values = BizMessageCode.values();
        for (BizMessageCode val : values) {
            if (val.getCode().equals(code)) {
                return val.getMessage();
            }
        }
        return "";
    }

}
