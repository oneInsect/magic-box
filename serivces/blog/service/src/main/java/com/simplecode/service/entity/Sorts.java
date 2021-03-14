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
@TableName("t_sorts")
@ApiModel(value="Sorts对象", description="")
public class Sorts implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID")
    @TableId(value = "sort_id", type = IdType.ID_WORKER_STR)
    private Long sortId;

    @ApiModelProperty(value = "分类名称")
    private String sortName;

    @ApiModelProperty(value = "分类别名")
    private String sortAlias;

    @ApiModelProperty(value = "分类描述")
    private String sortDescription;

    @ApiModelProperty(value = "父分类ID")
    private Long parentSortId;

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
