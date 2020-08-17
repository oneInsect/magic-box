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
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Accesskey对象", description="categories")
public class Accesskey implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Accesskey ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "Accesskey")
    private String accesskey;

    @ApiModelProperty(value = "logic delete 0: (false) exist, 1: (true) deleted")
    private Boolean idDeleted;

    @ApiModelProperty(value = "create time")
    private Date createdTime;


}
