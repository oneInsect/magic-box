package com.simplecode.common.utils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class SDResponse implements Serializable {
    private static final Long UID=1L;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public static SDResponse ok(){
        SDResponse SDResponse = new SDResponse();
        SDResponse.setStatus(true);
        SDResponse.setCode(ResultCode.SUCCESS);
        SDResponse.setMessage("success");
        return SDResponse;
    }

    public static SDResponse error(){
        SDResponse SDResponse = new SDResponse();
        SDResponse.setStatus(false);
        SDResponse.setCode(ResultCode.ERROR);
        SDResponse.setMessage("failed");
        return SDResponse;
    }

    public SDResponse status(Boolean status){
        this.setStatus(status);
        return this;
    }

    public SDResponse message(String message){
        this.setMessage(message);
        return this;
    }
    public SDResponse data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public SDResponse data(HashMap<String, Object> map){
        this.setData(map);
        return this;
    }

    @ApiModelProperty(value = "request status")
    private Boolean status;

    @ApiModelProperty(value = "response code")
    private Integer code;

    @ApiModelProperty(value = "message")
    private String message;

    @ApiModelProperty(value = "data of return")
    private HashMap<String, Object> data = new HashMap<String, Object>();
}
