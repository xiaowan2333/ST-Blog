package top.one.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.one.domain.Res;
import top.one.service.ArticleService;

/**
 * @author: XiaoWan
 * @Date: 2022/11/14
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 获取热门文章list
     * @return
     */
    @GetMapping("/hotArticleList")
    public Res getHotArticle(){
        return  articleService.getHotArticle();
    }

    /**
     * 获取所有（分类下）文章列表主页显示
     */
    @GetMapping("/articleList")
    public Res articleList(Integer pageNum,Integer pageSize,Integer categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    public Res getArticleDetail(@PathVariable("id")Long  id){
        return articleService.getArticleDetail(id);
    }
}
