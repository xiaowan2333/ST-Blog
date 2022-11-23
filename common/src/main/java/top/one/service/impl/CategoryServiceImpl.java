package top.one.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.one.constants.SystemConstants;
import top.one.dao.CategoryDao;
import top.one.domain.Res;
import top.one.domain.entity.Article;
import top.one.domain.entity.Category;
import top.one.domain.vo.CategoryVo;
import top.one.service.ArticleService;
import top.one.service.CategoryService;
import org.springframework.stereotype.Service;
import top.one.utils.BeanCopyUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-11-15 17:38:31
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public Res getCategoryList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //获取所有可见文章的全部类型集合id
        List<Article> articleList = articleService.list(queryWrapper);
        Set<Long> categorySet = articleList.stream().map(Article::getCategoryId)
                .collect(Collectors.toSet());
        //根据id集合查找所有可见集合
        List<Category> categories = listByIds(categorySet);
        categories = categories.stream().filter(cat -> SystemConstants.STATUS_NORMAL.equals(cat.getStatus())).collect(Collectors.toList());

        //转换VO
        ;
        return Res.okResult(BeanCopyUtils.copyBeanList(categories, CategoryVo.class));
    }
}

