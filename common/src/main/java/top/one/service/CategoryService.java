package top.one.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.one.domain.Res;
import top.one.domain.entity.Category;
import top.one.domain.entity.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-11-15 17:34:44
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取文章类别列表
     * @return
     */
    Res getCategoryList();
}

