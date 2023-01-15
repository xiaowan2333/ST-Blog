package top.one.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.one.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author xiaowan
 * @since 2023-01-15 12:15:02
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id获取角色信息
     * @param id
     * @return
     */
    List<String> selectRoleKeyByUserId(Long id);
}

