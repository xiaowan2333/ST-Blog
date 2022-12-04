package top.one.handle.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.one.domain.Res;
import top.one.enums.AppHttpCodeEnum;
import top.one.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: XiaoWan
 * @Date: 2022/12/4
 */
//认证统一异常处理
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        Res res =null;
        if (e instanceof BadCredentialsException){
            res = Res.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(),e.getMessage());
        }
        else if (e instanceof InsufficientAuthenticationException){
            res = Res.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }else {
            res = Res.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),"认证或授权失败！");
        }

        WebUtils.renderString(httpServletResponse, JSON.toJSONString(res));
    }
}
