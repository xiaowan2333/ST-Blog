package top.one;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.one.dao.ArticleDao;
import top.one.domain.entity.Article;
import top.one.service.ArticleService;

import java.util.ArrayList;

/**
 * @author: XiaoWan
 * @Date: 2022/12/6
 */
public class BatchUpdateTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleDao articleDao;

    @Test
    public void test1(){
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(Long.valueOf("1"), Long.valueOf(106)));
        articles.add(new Article(Long.valueOf("5"), Long.valueOf(58)));

//        articleService.updateById(articles.get(0));
        System.out.println(articles.get(0));
        articleService.updateBatchById(articles);
    }

}
