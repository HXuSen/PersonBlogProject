package com.hxsstu.runner;

import com.hxsstu.domain.entity.Article;
import com.hxsstu.mapper.ArticleMapper;
import com.hxsstu.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ClassName: ViewCountRunner
 * Package: com.hxsstu.runner
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-14:25
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        //存储到redis中
        redisCache.setCacheMap("article:viewCount",viewCountMap);

    }
}
