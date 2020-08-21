package com.simplecode.filemgt.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CateQuery {

    @ApiModelProperty(value = "Category Name")
    private String name;

    @ApiModelProperty(value = "Start Time")
    private String begin;

    @ApiModelProperty(value = "End Time")
    private String end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
