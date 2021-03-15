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
@TableName("t_articles")
@ApiModel(value="Articles对象", description="")
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博文ID")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    @ApiModelProperty(value = "发表用户ID")
    private Long userId;

    @ApiModelProperty(value = "博文标题")
    private String articleTitle;

    @ApiModelProperty(value = "博文内容")
    private String articleContent;

    @ApiModelProperty(value = "浏览量")
    private Long articleViews;

    @ApiModelProperty(value = "评论总数")
    private Long articleCommentCount;

    private Long articleLikeCount;

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
