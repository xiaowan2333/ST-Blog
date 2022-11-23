package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.one.domain.Res;
import top.one.service.LinkService;

/**
 * @author: XiaoWan
 * @Date: 2022/11/23
 */
@RestController
@RequestMapping("link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("getAllLink")
    public Res getAllLink(){
        return linkService.getAllLink();
    }
}
