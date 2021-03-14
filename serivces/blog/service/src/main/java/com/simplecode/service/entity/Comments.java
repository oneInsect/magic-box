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
@TableName("t_comments")
@ApiModel(value="Comments对象", description="")
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论ID")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    @ApiModelProperty(value = "发表用户ID")
    private Long userId;

    @ApiModelProperty(value = "评论博文ID")
    private Long articleId;

    @ApiModelProperty(value = "点赞数")
    private Long commentLikeCount;

    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    @ApiModelProperty(value = "父评论ID")
    private Long parentCommentId;

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
