package top.one.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.one.domain.entity.Article;
import top.one.service.ArticleService;
import top.one.utils.RedisCache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: XiaoWan
 * @Date: 2022/12/6
 */

/**
 * 定时任务，将redis中的浏览量同步到数据库
 */
@Component
public class UpdateViewCountJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount(){
        Map<String, Integer> cacheMap = redisCache.getCacheMap("article:viewCount");
        List<Article> collect = cacheMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), Long.valueOf(entry.getValue())))
                .collect(Collectors.toList());
        for (Article art :
                collect) {
            System.out.println("拿到缓存"+art.getViewCount());
            System.out.println("is"+art.getId());

        }
        //更新到数据库中
        articleService.updateBatchById(collect);
    }
}
