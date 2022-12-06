package top.one.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import top.one.dao.UserDao;
import top.one.domain.Res;
import top.one.domain.entity.User;
import top.one.domain.vo.UserInfoVo;
import top.one.enums.AppHttpCodeEnum;
import top.one.exception.SystemException;
import top.one.service.UserService;
import org.springframework.stereotype.Service;
import top.one.utils.BeanCopyUtils;
import top.one.utils.SecurityUtils;

import java.util.Objects;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-11-24 10:23:54
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 获取已登录用户的信息
     * @return
     */
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

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public Res register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //对密码加密
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        save(user);
        return Res.okResult();
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName,nickName);
        User one = getOne(queryWrapper);
        if (Objects.isNull(one)){
            return false;
        }
        return true;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        User one = getOne(queryWrapper);
        if (Objects.isNull(one)){
            return false;
        }
        return true;
    }

}

