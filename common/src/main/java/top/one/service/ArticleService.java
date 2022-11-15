package top.one.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.one.domain.Res;
import top.one.domain.entity.Article;
import top.one.domain.vo.HotArticleVo;

/**
 * 文章表(Article)表服务接口
 *
 * @author XiaoWan
 * @since 2022-11-14 21:01:02
 */
public interface ArticleService{

    /**
     * 获取最热文章列表
     * @return
     */
    Res getHotArticle();
}

