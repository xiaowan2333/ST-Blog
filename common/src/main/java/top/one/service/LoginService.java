package top.one.service;

import top.one.domain.Res;
import top.one.domain.entity.User;

/**
 * @author: XiaoWan
 * @Date: 2022/12/22
 */

/**
 * 后台登录service
 */
public interface LoginService {
    Res login(User user);

    Res logout();
}
