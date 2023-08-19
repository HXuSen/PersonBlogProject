package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-08-19 20:22:36
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

}

