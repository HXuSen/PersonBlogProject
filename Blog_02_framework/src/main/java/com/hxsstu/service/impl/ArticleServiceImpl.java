package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.constants.SystemConstants;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.entity.Article;
import com.hxsstu.domain.vo.HotArticleVo;
import com.hxsstu.mapper.ArticleMapper;
import com.hxsstu.service.ArticleService;
import com.hxsstu.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ArticleServiceImpl
 * Package: com.hxsstu.service.impl
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/19-16:37
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章,返回ResponseResult
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //status=1,delFlag=0,DESC,limit=10
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();
        //Bean拷贝
        //List<HotArticleVo> articleVos = new ArrayList<>();
        //for (Article article : articles) {
        //    HotArticleVo vo = new HotArticleVo();
        //    BeanUtils.copyProperties(article,vo);
        //    articleVos.add(vo);
        //}
        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(articleVos);
    }
}
