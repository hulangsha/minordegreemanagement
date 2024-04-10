package com.sicau.minordegreemanagement.common.component;

public enum CommonCode {
    SUCCESS(200, "请求成功"),
    Fail_SERVER(500,"服务器出错"),
    Fail_CLIENT(400, "请求出错"),
    UNAUTHORIZED(401,"没有操作权限"),
    CONST_NUMBER_ONE(1, "常量1"),
    CONST_NUMBER_ZERO(0, "常量0")
    ;
    /**
    * 请求成功
    * */
    private String message;

    private Integer code;

    CommonCode(Integer code, String message) {

        this.code = code;
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    public Integer getCode() {
        return code;
    }


}
