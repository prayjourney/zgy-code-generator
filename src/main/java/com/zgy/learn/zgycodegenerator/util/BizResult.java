package com.zgy.learn.zgycodegenerator.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 统一的返回信息
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BizResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private T data;
    private int code;
    private String message;

    public BizResult(T data) {
        this.data = data;
        this.code = 0;
        this.message = "";
    }

    public BizResult(T data, String message) {
        this.data = data;
        this.code = 0;
        this.message = message;
    }

    public BizResult(T data, BizMessageCode messageCode) {
        this.data = data;
        this.code = messageCode.getCode();
        this.message = messageCode.getMessage();
    }

    public BizResult(Throwable e, BizMessageCode messageCode) {
        this.code = messageCode.getCode();
        this.message = e.getMessage();
    }

}