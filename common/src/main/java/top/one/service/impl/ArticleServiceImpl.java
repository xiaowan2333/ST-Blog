package top.one.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.one.constants.SystemConstants;
import top.one.dao.ArticleDao;
import top.one.domain.Res;
import top.one.domain.entity.Article;
import top.one.domain.vo.HotArticleVo;
import top.one.service.ArticleService;
import org.springframework.stereotype.Service;
import top.one.utils.BeanCopyUtils;

import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author XiaoWan
 * @since 2022-11-14 21:01:02
 */
@Service("articleService")
public class ArticleServiceImpl  implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Override
    public Res getHotArticle() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //文章状态是正常状态
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按浏览量降序排
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1,10);
        List<Article> records = articleDao.selectPage(page, queryWrapper).getRecords();
        return Res.okResult(BeanCopyUtils.copyBeanList(records,HotArticleVo.class));
    }
}

