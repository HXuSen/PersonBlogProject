package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CategoryController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/19-20:29
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}
