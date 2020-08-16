package com.simplecode.filemgt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * categories
 * </p>
 *
 * @author testjava
 * @since 2020-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Categories对象", description="categories")
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Category ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "Category Name")
    private String name;

    @ApiModelProperty(value = "Category Describe")
    private String desc;

    @ApiModelProperty(value = "Category Created Time")
    private Date createdTime;

    @ApiModelProperty(value = "Category Modified Time")
    private Date modifiedTime;

    @ApiModelProperty(value = "logic delete 0: (false) exist, 1: (true) deleted")
    private Boolean isDeleted;


}
