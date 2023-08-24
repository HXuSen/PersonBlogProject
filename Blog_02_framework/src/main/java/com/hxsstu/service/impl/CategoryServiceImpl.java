package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.constants.SystemConstants;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.CategoryDto;
import com.hxsstu.domain.entity.Article;
import com.hxsstu.domain.entity.Category;
import com.hxsstu.domain.vo.CategoryUpdateVo1;
import com.hxsstu.domain.vo.CategoryVo;
import com.hxsstu.domain.vo.PageVo;
import com.hxsstu.mapper.CategoryMapper;
import com.hxsstu.service.ArticleService;
import com.hxsstu.service.CategoryService;
import com.hxsstu.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Category::getStatus,SystemConstants.CATEGORY_STATUS_NORMAL);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list(queryWrapper), CategoryVo.class);
        return categoryVos;
    }

    @Override
    public ResponseResult categoryList(Integer pageNum, Integer pageSize, CategoryDto categoryDto) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.hasText(categoryDto.getName()),Category::getName,categoryDto.getName());
        queryWrapper.eq(StringUtils.hasText(categoryDto.getStatus()),Category::getStatus,categoryDto.getStatus());

        Page<Category> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(),page.getTotal()));
    }

    @Override
    public ResponseResult addCategory(CategoryDto categoryDto) {
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateCategoryById(Category category) {
        updateById(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getCategoryById(Long id) {
        Category category = getById(id);
        CategoryUpdateVo1 categoryUpdateVo1 = BeanCopyUtils.copyBean(category, CategoryUpdateVo1.class);
        return ResponseResult.okResult(categoryUpdateVo1);
    }
}

