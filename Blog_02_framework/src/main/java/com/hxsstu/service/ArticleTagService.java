package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.entity.ArticleTag;

import java.util.List;


/**
 * 文章标签关联表(ArticleTag)表服务接口
 *
 * @author makejava
 * @since 2023-08-22 11:39:49
 */
public interface ArticleTagService extends IService<ArticleTag> {

    List<Long> getTagsByArticleId(Long id);

    void removeByArticleId(Long id);
}

