package top.one.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.one.constants.SystemConstants;
import top.one.dao.ArticleDao;
import top.one.domain.Res;
import top.one.domain.entity.Article;
import top.one.domain.entity.Category;
import top.one.domain.vo.ArticleDetailVo;
import top.one.domain.vo.ArticleListVo;
import top.one.domain.vo.HotArticleVo;
import top.one.domain.vo.PageVo;
import top.one.service.ArticleService;
import org.springframework.stereotype.Service;
import top.one.service.CategoryService;
import top.one.utils.BeanCopyUtils;
import top.one.utils.RedisCache;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 *
 * @author XiaoWan
 * @since 2022-11-14 21:01:02
 */
@Service("articleService")
public class ArticleServiceImpl  extends ServiceImpl<ArticleDao,Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取热门文章标题
     * @return
     */
    @Override
    public Res getHotArticle() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //文章状态是正常状态
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按浏览量降序排
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1,10);
        List<Article> records = page(page, queryWrapper).getRecords();
        return Res.okResult(BeanCopyUtils.copyBeanList(records,HotArticleVo.class));
    }

    /**
     * 获取所有文章列表分页
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    public Res articleList(Integer pageNum, Integer pageSize, Integer categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对置顶进行降序排
        queryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        //根据List中的categoryId查询出categoryName
        List<Article> records = page.getRecords();
        List<Article> collect = records.stream()
                .map(new Function<Article, Article>() {

                    @Override
                    public Article apply(Article article) {
                        Category categoryId = categoryService.getById(article.getCategoryId());
                        article.setCategoryName(categoryId.getName());
                        return article;
                    }
                })
                .collect(Collectors.toList());
        //查询到结果封装VO
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(collect, ArticleListVo.class);
        return Res.okResult(new PageVo(articleListVos,page.getTotal()));
    }

    /**
     * 根据id获取文章详情
     * @param id
     * @return
     */
    @Override
    public Res getArticleDetail(Long id) {
        //find article by id
        Article article = getById(id);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据所属分类id查出分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //从redis获取浏览量
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        articleDetailVo.setViewCount(viewCount.longValue());
        return Res.okResult(articleDetailVo);
    }

    @Override
    public Res updateViewCount(Long id) {
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return Res.okResult();
    }
}

