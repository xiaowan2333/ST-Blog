package top.one.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.one.annotion.SystemLog;
import top.one.domain.Res;
import top.one.service.LinkService;

/**
 * @author: XiaoWan
 * @Date: 2022/11/23
 */
@RestController
@RequestMapping("link")
@ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @SystemLog(businessName = "获取所有友链列表")
    @GetMapping("getAllLink")
    public Res getAllLink(){
        return linkService.getAllLink();
    }
}
