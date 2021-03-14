package com.simplecode.service.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_labels")
@ApiModel(value="Labels对象", description="")
public class Labels implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标签ID")
    @TableId(value = "label_id", type = IdType.AUTO)
    private Long labelId;

    @ApiModelProperty(value = "标签名称")
    private String labelName;

    @ApiModelProperty(value = "标签别名")
    private String labelAlias;

    @ApiModelProperty(value = "标签描述")
    private String labelDescription;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    @ApiModelProperty(value = "logic delete 0: (false) exist, 1: (true) deleted")
    @TableLogic
    private Integer isDeleted;


}
