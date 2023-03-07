package com.tangya.jwt.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 5778573516446596671L;
    private static int SUCCESS = 0;
    private static int FAIL = -1;
    private static String MSG_SUCCESS = "成功";
    private static String MSG_WARNING = "成功但有告警";
    private static String MSG_FAIL = "失败";
    private int code = 0;
    private String type;
    private String message;
    private T data;

    public Result() {
    }

    public Result(int code, String type, String message, T data) {
        this.code = code;
        this.type = type;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result(SUCCESS, Result.ResponseType.TYPE_SUCCESS.getType(), MSG_SUCCESS, data);
    }

    public static <T> Result<T> success(String message, T data) {
        message = message != null && message.length() > 0 ? message : MSG_SUCCESS;
        return new Result(SUCCESS, Result.ResponseType.TYPE_SUCCESS.getType(), message, data);
    }

    public static <T> Result<T> info(int code, String message, T data) {
        message = message != null && message.length() > 0 ? message : MSG_SUCCESS;
        return new Result(code, Result.ResponseType.TYPE_INFO.getType(), message, data);
    }

    public static <T> Result<T> warning(int code, String message, T data) {
        message = message != null && message.length() > 0 ? message : MSG_WARNING;
        return new Result(code, Result.ResponseType.TYPE_WARNING.getType(), message, data);
    }

    public static <T> Result<T> error(int code, String message, T data) {
        message = message != null && message.length() > 0 ? message : MSG_FAIL;
        return new Result(code, Result.ResponseType.TYPE_ERROR.getType(), message, data);
    }

    public static <T> Result<T> fail(T data) {
        return new Result(FAIL, Result.ResponseType.TYPE_ERROR.getType(), MSG_FAIL, data);
    }

    public static <T> Result<T> fail(String message, T data) {
        message = message != null && message.length() > 0 ? message : MSG_FAIL;
        return new Result(FAIL, Result.ResponseType.TYPE_ERROR.getType(), message, data);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected static enum ResponseType {
        TYPE_SUCCESS("success"),
        TYPE_INFO("info"),
        TYPE_WARNING("warning"),
        TYPE_ERROR("error");

        private String type;

        private ResponseType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }
}

