package com.simplecode.common.utils;


import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;

public class SelfDefineResponse {

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

    public static SelfDefineResponse ok(){
        SelfDefineResponse selfDefineResponse = new SelfDefineResponse();
        selfDefineResponse.setStatus(true);
        selfDefineResponse.setCode(ResultCode.SUCCESS);
        selfDefineResponse.setMessage("success");
        return selfDefineResponse;
    }

    public static SelfDefineResponse error(){
        SelfDefineResponse selfDefineResponse = new SelfDefineResponse();
        selfDefineResponse.setStatus(false);
        selfDefineResponse.setCode(ResultCode.ERROR);
        selfDefineResponse.setMessage("failed");
        return selfDefineResponse;
    }

    public SelfDefineResponse status(Boolean status){
        this.setStatus(status);
        return this;
    }

    public SelfDefineResponse message(String message){
        this.setMessage(message);
        return this;
    }
    public SelfDefineResponse data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public SelfDefineResponse data(HashMap<String, Object> map){
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
