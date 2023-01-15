package top.one.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.one.constants.SystemConstants;
import top.one.dao.MenuDao;
import top.one.domain.entity.Menu;
import top.one.service.MenuService;
import top.one.utils.SecurityUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author xiaowan
 * @since 2023-01-15 12:14:38
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有权限
        if(id == 1l) {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            queryWrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> list = list(queryWrapper);
            List<String> perms = list.stream().map(Menu::getPerms).collect(Collectors.toList());
            return perms;
        }
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuDao menuDao = getBaseMapper();
        List<Menu> menus = null;
        if (SecurityUtils.isAdmin()){
            menus = menuDao.selectAllRouterMenu();
        }else{
            menus = menuDao.selectRouterMenuByUserId(userId);
        }
        List<Menu> tree = buildMenuTree(menus,0l);
        return tree;
    }

    /**
     * 构造menuTree
     * @param menus
     * @return
     */
    private List<Menu> buildMenuTree(List<Menu> menus,Long parenId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parenId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> child = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
        return child;
    }
}

