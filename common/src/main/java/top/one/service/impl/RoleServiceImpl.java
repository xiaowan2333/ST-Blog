package top.one.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.one.dao.RoleDao;
import top.one.domain.entity.Role;
import top.one.service.RoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author xiaowan
 * @since 2023-01-15 12:15:02
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        if(id == 1L){
            List<String> roleKey = new ArrayList<>();
            roleKey.add("admin");
            return roleKey;
        }
        return getBaseMapper().selectRoleByUserId(id);
    }
}

