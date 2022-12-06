package top.one.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.one.annotion.SystemLog;
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
    @SystemLog(businessName = "获取热门文章列表")
    @GetMapping("/hotArticleList")
    public Res getHotArticle(){
        return  articleService.getHotArticle();
    }

    /**
     * 获取所有（分类下）文章列表主页显示
     */
    @SystemLog(businessName = "获取所有文章列表")
    @GetMapping("/articleList")
    public Res articleList(Integer pageNum,Integer pageSize,Integer categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @SystemLog(businessName = "根据id获取文章详情")
    @GetMapping("/{id}")
    public Res getArticleDetail(@PathVariable("id")Long  id){
        return articleService.getArticleDetail(id);
    }

    @SystemLog(businessName = "增加浏览量")
    @PutMapping("/updateViewCount/{id}")
    public Res updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }
}
