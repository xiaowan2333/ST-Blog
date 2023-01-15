package top.one.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.one.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author xiaowan
 * @since 2023-01-15 12:14:34
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户id查询用户权限信息
     * @param id
     * @return
     */
    List<String> selectPermsByUserId(Long id);

    /**
     * 根据用户id获取前端路由表
     * @param userId
     * @return
     */
    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}

