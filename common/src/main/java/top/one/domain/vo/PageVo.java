package top.one.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: XiaoWan
 * @Date: 2022/11/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    private List rows;
    private Long total;
}
