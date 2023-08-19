package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.constants.SystemConstants;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.Article;
import com.hxsstu.domain.entity.Category;
import com.hxsstu.domain.vo.CategoryVo;
import com.hxsstu.mapper.CategoryMapper;
import com.hxsstu.service.ArticleService;
import com.hxsstu.service.CategoryService;
import com.hxsstu.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-08-19 20:22:37
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list(articleQueryWrapper);
        Set<Long> categoryIds = articles.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        List<Category> categories = listByIds(categoryIds);
        List<Category> categoryList = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }
}

