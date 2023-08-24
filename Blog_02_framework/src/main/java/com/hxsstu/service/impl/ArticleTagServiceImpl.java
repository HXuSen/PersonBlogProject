package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.domain.entity.ArticleTag;
import com.hxsstu.mapper.ArticleTagMapper;
import com.hxsstu.service.ArticleTagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-08-22 11:39:50
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Override
    public List<Long> getTagsByArticleId(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId,id);
        List<Long> tagIds = list(queryWrapper).stream()
                .map(articleTag -> articleTag.getTagId())
                .collect(Collectors.toList());
        return tagIds;
    }

    @Override
    public void removeByArticleId(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId,id);
        remove(queryWrapper);
    }
}

