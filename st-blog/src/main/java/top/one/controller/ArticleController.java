package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.one.domain.Res;
import top.one.domain.vo.HotArticleVo;
import top.one.service.ArticleService;

/**
 * @author: XiaoWan
 * @Date: 2022/11/14
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("hotArticleList")
    public Res getHotArticle(){
        return  articleService.getHotArticle();
    }
}
