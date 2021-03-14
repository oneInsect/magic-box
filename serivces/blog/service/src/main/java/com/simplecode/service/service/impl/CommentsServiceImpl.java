package com.simplecode.service.service.impl;

import com.simplecode.service.entity.Comments;
import com.simplecode.service.mapper.CommentsMapper;
import com.simplecode.service.service.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-14
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

}
