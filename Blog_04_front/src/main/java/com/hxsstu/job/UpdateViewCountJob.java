package com.hxsstu.job;

import com.hxsstu.domain.entity.Article;
import com.hxsstu.service.ArticleService;
import com.hxsstu.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: TestJob
 * Package: com.hxsstu.job
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-14:27
 */
@Component
public class UpdateViewCountJob {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        List<Article> collect = viewCountMap.entrySet().stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(collect);
    }
}
