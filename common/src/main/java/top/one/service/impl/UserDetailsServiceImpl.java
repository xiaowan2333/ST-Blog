package top.one.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.one.dao.UserDao;
import top.one.domain.entity.LoginUser;
import top.one.domain.entity.User;

import java.util.Objects;

/**
 * @author: XiaoWan
 * @Date: 2022/12/3
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库这个用户，有就封装传出，没有就直接抛异常
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userDao.selectOne(queryWrapper);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }
        //TODO 查询权限信息
        return new LoginUser(user);
    }
}
