package com.sicau.minordegreemanagement.common.result;

import com.sicau.minordegreemanagement.common.component.CommonCode;
import lombok.Data;

import java.io.Serializable;
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    //成功时返回的结果
    public Result<T> success(Integer code, String msg){
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result<T> success(String msg){
        return success(CommonCode.SUCCESS.getCode(),msg);
    }
    public Result<T> success(){
        return success(CommonCode.SUCCESS.getMessage());
    }

    public Result<T> put(T data){
        this.data = data;
        return this;
    }

    //失败时返回的结果
    public Result<T> fail(Integer code,String msg){
        this.code = code;
        this.msg = msg;
        return this;
    }
    public Result<T> fail(String msg){
        return fail(CommonCode.Fail_SERVER.getCode(), msg);
    }
    public Result<T> fail(){
        return fail(CommonCode.Fail_SERVER.getMessage());
    }

}
