package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-08-20 17:55:57
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

