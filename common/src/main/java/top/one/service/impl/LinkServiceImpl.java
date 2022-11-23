package top.one.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.one.constants.SystemConstants;
import top.one.dao.LinkDao;
import top.one.domain.Res;
import top.one.domain.entity.Link;
import top.one.domain.vo.LinkVo;
import top.one.service.LinkService;
import org.springframework.stereotype.Service;
import top.one.utils.BeanCopyUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 23:23:08
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkDao, Link> implements LinkService {

    /**
     * 获取所有友链
     * @return
     */
    @Override
    public Res getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        //只要审审核通过的
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return Res.okResult(linkVos);
    }
}

