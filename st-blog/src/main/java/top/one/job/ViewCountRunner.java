package top.one.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.one.dao.ArticleDao;
import top.one.domain.entity.Article;
import top.one.utils.RedisCache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: XiaoWan
 * @Date: 2022/12/6
 */

/**
 * 项目启动后将所有博客浏览量存入redis
 */
@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        List<Article> articleList = articleDao.selectList(null);
        Map<String, Integer> collect = articleList.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(),
                        article -> article.getViewCount().intValue()));
        redisCache.setCacheMap("article:viewCount",collect);
    }
}
