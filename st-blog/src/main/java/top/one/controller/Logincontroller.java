package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.one.domain.Res;
import top.one.domain.entity.User;
import top.one.service.BlogLoginService;

/**
 * @author: XiaoWan
 * @Date: 2022/12/3
 */
@RestController
public class Logincontroller {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public Res login(@RequestBody User user){
    return blogLoginService.login(user);
    }
}
