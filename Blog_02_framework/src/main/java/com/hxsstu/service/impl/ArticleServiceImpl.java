package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.constants.SystemConstants;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.ArticleDto;
import com.hxsstu.domain.entity.Article;
import com.hxsstu.domain.entity.ArticleTag;
import com.hxsstu.domain.entity.Category;
import com.hxsstu.domain.vo.*;
import com.hxsstu.mapper.ArticleMapper;
import com.hxsstu.service.ArticleService;
import com.hxsstu.service.ArticleTagService;
import com.hxsstu.service.CategoryService;
import com.hxsstu.utils.BeanCopyUtils;
import com.hxsstu.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleTagService articleTagService;

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

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //判断请求是否带有categoryId
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0,Article::getCategoryId,categoryId);
        //文章status=1
        queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行排序
        queryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page(pageNum,pageSize);
        page(page, queryWrapper);
        //查询categoryName
        List<Article> articles = page.getRecords();
        //for (Article article : articles) {
        //    Category category = categoryService.getById(article.getCategoryId());
        //    article.setCategoryName(category.getName());
        //}
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据ID查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类ID查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult addArticle(ArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);

        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult articleListAdmin(Integer pageNum, Integer pageSize, ArticleDto articleDto) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like(StringUtils.hasText(articleDto.getTitle()),Article::getTitle,articleDto.getTitle());
        queryWrapper.like(StringUtils.hasText(articleDto.getSummary()),Article::getSummary,articleDto.getSummary());
        queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);

        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        List<ArticleListAdminVo> articleListAdminVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListAdminVo.class);
        return ResponseResult.okResult(new PageVo(articleListAdminVos,page.getTotal()));
    }

    @Override
    public ResponseResult getArticleById(Long id) {
        Article article = getById(id);
        List<Long> tags = articleTagService.getTagsByArticleId(id);
        ArticleDto articleDto = BeanCopyUtils.copyBean(article, ArticleDto.class);
        articleDto.setTags(tags);
        return ResponseResult.okResult(articleDto);
    }

    @Override
    public ResponseResult updateArticle(ArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        updateById(article);

        articleTagService.removeByArticleId(article.getId());
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }
}
