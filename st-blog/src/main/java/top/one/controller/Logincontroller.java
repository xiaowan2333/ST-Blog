package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.one.annotion.SystemLog;
import top.one.domain.Res;
import top.one.domain.entity.User;
import top.one.enums.AppHttpCodeEnum;
import top.one.exception.SystemException;
import top.one.service.BlogLoginService;

/**
 * @author: XiaoWan
 * @Date: 2022/12/3
 */
@RestController
public class Logincontroller {
    @Autowired
    private BlogLoginService blogLoginService;

    @SystemLog(businessName = "登录接口")
    @PostMapping("/login")
    public Res login(@RequestBody User user){
        if (!(StringUtils.hasText(user.getUserName())&&StringUtils.hasText(user.getPassword()))){
            //当用户名或密码为空时抛出异常
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
    return blogLoginService.login(user);
    }

    @SystemLog(businessName = "登出接口")
    @PostMapping("logout")
    public Res logout(){
        return blogLoginService.logout();
    }
}
