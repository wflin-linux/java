package com.wflin.Util;

import lombok.Data;

@Data
public class Response<T> {

    private Integer code;
    private String message;
    private T data;

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(Integer code) {
        this.code = code;
    }
}
