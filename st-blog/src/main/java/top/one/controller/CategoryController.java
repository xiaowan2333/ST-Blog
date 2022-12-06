package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.one.annotion.SystemLog;
import top.one.domain.Res;
import top.one.service.CategoryService;

/**
 * @author: XiaoWan
 * @Date: 2022/11/15
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取文章类别列表
     * @return
     */
    @SystemLog(businessName = "获取文章类别列表")
    @GetMapping("getCategoryList")
    public Res getCategoryLisy(){
        return categoryService.getCategoryList();
    }
}
