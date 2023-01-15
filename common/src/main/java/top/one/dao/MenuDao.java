package top.one.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.one.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author xiaowan
 * @since 2023-01-15 12:14:32
 */
@Mapper
public interface MenuDao extends BaseMapper<Menu> {

    /**
     * 根据用户id查询对应的权限列表
     * @param id
     * @return
     */
    List<String> selectPermsByUserId(Long id);

    /**
     * 获取所有的前端路由菜单
     * @return
     */
    List<Menu> selectAllRouterMenu();

    /**
     * 根据id查询对应的用户的前端路由菜单
     * @param userId
     * @return
     */
    List<Menu> selectRouterMenuByUserId(Long userId);
}

