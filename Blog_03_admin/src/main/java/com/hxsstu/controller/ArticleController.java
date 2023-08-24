package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.ArticleDto;
import com.hxsstu.service.ArticleService;
import com.hxsstu.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ArticleController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-11:36
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTagService articleTagService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody ArticleDto articleDto){
        return articleService.addArticle(articleDto);
    }

    @GetMapping("/list")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, ArticleDto articleDto){
        return articleService.articleListAdmin(pageNum,pageSize,articleDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticle(@PathVariable("id")Long id){
        return articleService.getArticleById(id);
    }

    @PutMapping
    public ResponseResult updateArticle(@RequestBody ArticleDto articleDto){
        return articleService.updateArticle(articleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable("id")Long id){
        articleService.removeById(id);
        articleTagService.removeByArticleId(id);
        return ResponseResult.okResult();
    }
}
