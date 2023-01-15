package top.one.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.one.domain.entity.Menu;
import java.util.List;

/**
 * @author: XiaoWan
 * @Date: 2023/1/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {

    private List<Menu> menus;
}
