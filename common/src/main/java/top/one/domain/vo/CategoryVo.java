package top.one.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: XiaoWan
 * @Date: 2022/11/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo implements Serializable {
    private Long id;
    private String name;
}
