package com.hxsstu.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.CategoryDto;
import com.hxsstu.domain.entity.Category;
import com.hxsstu.domain.vo.CategoryVo;
import com.hxsstu.domain.vo.ExcelCategoryVo;
import com.hxsstu.enums.AppHttpCodeEnum;
import com.hxsstu.service.CategoryService;
import com.hxsstu.utils.BeanCopyUtils;
import com.hxsstu.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: CategoryController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-11:19
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        //设置下载文件的请求头
        try {
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categories = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categories, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE)
                    .sheet("分类导出").doWrite(excelCategoryVos);
        } catch (Exception e) {
            e.printStackTrace();
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    @GetMapping("/list")
    public ResponseResult categoryList(Integer pageNum, Integer pageSize, CategoryDto categoryDto){
        return categoryService.categoryList(pageNum,pageSize,categoryDto);
    }

    @PostMapping
    public ResponseResult addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getCategory(@PathVariable("id")Long id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping
    public ResponseResult updateCategory(@RequestBody Category category){
        return categoryService.updateCategoryById(category);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteCategory(@PathVariable("id") Long id){
        categoryService.removeById(id);
        return ResponseResult.okResult();
    }
}
