package top.one.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.one.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author xiaowan
 * @since 2023-01-15 12:15:02
 */
public interface RoleDao extends BaseMapper<Role> {

    /**
     * 根据用户id查询用户角色
     * @param id
     * @return
     */
    List<String> selectRoleByUserId(Long id);
}

