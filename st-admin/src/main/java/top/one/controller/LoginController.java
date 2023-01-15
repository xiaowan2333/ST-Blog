package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.one.domain.Res;
import top.one.domain.entity.LoginUser;
import top.one.domain.entity.Menu;
import top.one.domain.entity.User;
import top.one.domain.vo.AdminUserInfoVo;
import top.one.domain.vo.RoutersVo;
import top.one.domain.vo.UserInfoVo;
import top.one.enums.AppHttpCodeEnum;
import top.one.exception.SystemException;
import top.one.service.LoginService;
import top.one.service.MenuService;
import top.one.service.RoleService;
import top.one.utils.BeanCopyUtils;
import top.one.utils.SecurityUtils;

import java.util.List;

/**
 * @author: XiaoWan
 * @Date: 2022/12/22
 */

/**
 * 后端登录接口
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public Res login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @GetMapping("getInfo")
    public Res<AdminUserInfoVo> getInfo() {
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return Res.okResult(adminUserInfoVo);
    }

    @GetMapping("getRouters")
    public Res<RoutersVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return Res.okResult(new RoutersVo(menus));
    }
}
