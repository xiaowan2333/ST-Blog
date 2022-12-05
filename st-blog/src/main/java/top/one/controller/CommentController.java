package top.one.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public Res commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }
    @GetMapping("/linkCommentList")
    public Res linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }

    @PostMapping
    public Res addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
