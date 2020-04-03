package com.luo.util;

import lombok.Getter;

@Getter
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(200, "success", data);
    }

    public static <T> Result<T> success() {
        return new Result<T>(200, "success", null);
    }

    public static <T> Result<T> error() {
        return new Result<T>(500, "fail", null);
    }

    public static <T> Result<T> error(T data) {
        return new Result<T>(500, "fail", data);
    }

}
