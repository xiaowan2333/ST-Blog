package top.one.service;

import top.one.domain.Res;
import top.one.domain.entity.User;

/**
 * @author: XiaoWan
 * @Date: 2022/12/3
 */
public interface BlogLoginService {

    Res login(User user);

    Res logout();

}
