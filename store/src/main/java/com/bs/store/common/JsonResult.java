package com.bs.store.common;


public class JsonResult {
    private String code;
    private String message;
    private Object data;

    public JsonResult() {
    }
    public JsonResult(String code, String s, Object o) {
        this.setCode(code);
        this.setMessage(s);
        this.setData(o);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JsonResult success() {
        return new JsonResult(Constants.CODE_200, "", null);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(Constants.CODE_200, "", data);
    }

    public static JsonResult error(String code, String msg) {
        return new JsonResult(code, msg, null);
    }

    public static JsonResult error() {
        return new JsonResult(Constants.CODE_500, "系统错误", null);
    }


}
