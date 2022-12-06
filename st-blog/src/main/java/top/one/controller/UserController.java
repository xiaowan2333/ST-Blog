package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.one.annotion.SystemLog;
import top.one.domain.Res;
import top.one.domain.entity.User;
import top.one.service.UserService;

/**
 * @author: XiaoWan
 * @Date: 2022/12/5
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @SystemLog(businessName = "获取已登录用户信息")
    @GetMapping("/userInfo")
    public Res userInfo(){
        return userService.userInfo();
    }

    @SystemLog(businessName = "更新已登录用户信息")
    @PutMapping("/userInfo")
    public Res updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @SystemLog(businessName = "用户注册")
    @PostMapping("/register")
    public Res register(@RequestBody User user){
        return userService.register(user);
    }
}
