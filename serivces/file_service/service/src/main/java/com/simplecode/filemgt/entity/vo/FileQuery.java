package com.simplecode.filemgt.entity.vo;

import io.swagger.annotations.ApiModelProperty;

public class FileQuery {

    @ApiModelProperty(value = "File Path")
    private String path;

    @ApiModelProperty(value = "Start Time")
    private String begin;

    @ApiModelProperty(value = "End Time")
    private String end;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
