package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/userInfo")
    public Res userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    public Res updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
}
