package top.one.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.one.annotion.SystemLog;
import top.one.constants.SystemConstants;
import top.one.domain.Res;
import top.one.domain.entity.Comment;
import top.one.service.CommentService;

/**
 * @author: XiaoWan
 * @Date: 2022/12/4
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @SystemLog(businessName = "获取文章评论")
    @GetMapping("/commentList")
    public Res commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    }
    )
    @SystemLog(businessName = "获取友链下评论")
    @GetMapping("/linkCommentList")
    public Res linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }

    @SystemLog(businessName = "添加评论")
    @PostMapping
    public Res addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
