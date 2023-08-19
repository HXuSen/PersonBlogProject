package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.Article;
import com.hxsstu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: ArticleController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/19-16:42
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //@GetMapping("/list")
    //public List<Article> test(){
    //    return articleService.list();
    //}
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        //查询热门文章
        ResponseResult result = articleService.hotArticleList();
        return result;
    }
}