package top.one.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.one.domain.entity.Article;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author XiaoWan
 * @since 2022-11-14 21:01:01
 */
@Mapper
public interface ArticleDao extends BaseMapper<Article> {

}

