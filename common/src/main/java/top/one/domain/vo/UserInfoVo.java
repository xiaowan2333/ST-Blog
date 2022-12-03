package top.one.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: XiaoWan
 * @Date: 2022/12/3
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    private Long id;
    //昵称
    private String nickName;
    //头像
    private String avatar;
    //邮箱
    private String email;
    //用户性别（0男，1女，2未知）
    private String sex;
}
