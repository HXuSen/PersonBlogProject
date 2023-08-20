package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.Article;

/**
 * ClassName: ArticleService
 * Package: com.hxsstu.service
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/19-16:36
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
