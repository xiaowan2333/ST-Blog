package top.one.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.one.dao.UserDao;
import top.one.domain.Res;
import top.one.domain.entity.User;
import top.one.domain.vo.UserInfoVo;
import top.one.service.UserService;
import org.springframework.stereotype.Service;
import top.one.utils.BeanCopyUtils;
import top.one.utils.SecurityUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-11-24 10:23:54
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public Res userInfo() {
        //获取当前登录user信息
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return Res.okResult(userInfoVo);
    }

    /**
     * 根据前端传来的信息更新用户资料
     * @param user
     * @return
     */
    @Override
    public Res updateUserInfo(User user) {
        updateById(user);
        return Res.okResult();
    }
}

