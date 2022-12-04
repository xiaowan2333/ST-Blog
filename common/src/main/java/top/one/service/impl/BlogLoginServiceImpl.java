package top.one.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import top.one.domain.Res;
import top.one.domain.entity.LoginUser;
import top.one.domain.entity.User;
import top.one.domain.vo.BlogUserLoginVo;
import top.one.domain.vo.UserInfoVo;
import top.one.service.BlogLoginService;
import top.one.utils.BeanCopyUtils;
import top.one.utils.JwtUtil;
import top.one.utils.RedisCache;

import java.util.Objects;

/**
 * @author: XiaoWan
 * @Date: 2022/12/3
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public Res login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)) {
            //BadCredentialsException
            throw new RuntimeException("用户名或密码错误！！");
        }
        //获取用户id，生成token并存入redis中
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把token和userinfo封装返回
        redisCache.setCacheObject("bloglogin"+userId,loginUser);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        return new Res().ok(new BlogUserLoginVo(jwt,userInfoVo));
    }
}
