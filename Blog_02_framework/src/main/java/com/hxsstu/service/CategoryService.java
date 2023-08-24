package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.CategoryDto;
import com.hxsstu.domain.entity.Category;
import com.hxsstu.domain.vo.CategoryVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-08-19 20:22:36
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    ResponseResult categoryList(Integer pageNum, Integer pageSize, CategoryDto categoryDto);

    ResponseResult addCategory(CategoryDto categoryDto);

    ResponseResult updateCategoryById(Category category);

    ResponseResult getCategoryById(Long id);
}

