package com.simplecode.service.controller;


import com.simplecode.common.exception.GlobalException;
import com.simplecode.common.utils.SDResponse;
import com.simplecode.service.entity.Comments;
import com.simplecode.service.entity.Comments;
import com.simplecode.service.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/service/comments")
public class CommentsController {
    
    @Autowired
    CommentsService commentsService;


    @PostMapping("comment")
    public SDResponse addComment(@RequestBody(required = true) Comments comments){
        commentsService.addComment(comments);
        return SDResponse.ok();
    }

    @GetMapping("{articleId}")
    public SDResponse findComment(@PathVariable String articleId) throws GlobalException {
        List<Comments> comments = commentsService.findCommentsByArticleId(articleId);
        return SDResponse.ok().data("comments", comments);
    }

    @DeleteMapping("{commentId}")
    public SDResponse removeComment(@PathVariable String commentId) throws GlobalException {
        commentsService.removeComment(commentId);
        return SDResponse.ok();
    }
    
}

