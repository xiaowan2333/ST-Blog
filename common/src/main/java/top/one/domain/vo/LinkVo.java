package top.one.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: XiaoWan
 * @Date: 2022/11/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {
    private Long id;

    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;
}
