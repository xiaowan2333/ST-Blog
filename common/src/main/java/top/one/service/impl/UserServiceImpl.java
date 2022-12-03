package top.one.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.one.dao.UserDao;
import top.one.domain.entity.User;
import top.one.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-11-24 10:23:54
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

