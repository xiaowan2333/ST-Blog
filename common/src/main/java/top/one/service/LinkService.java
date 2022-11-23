package top.one.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.one.domain.Res;
import top.one.domain.entity.Link;

/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 23:23:08
 */
public interface LinkService extends IService<Link> {

    /**
     * 获取所有友链
     * @return
     */
    Res getAllLink();

}

