package top.one.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.one.domain.Res;
import top.one.domain.entity.Comment;

/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-12-04 20:44:28
 */
public interface CommentService extends IService<Comment> {

    Res commentList(String commentType,Long articleId, Integer pageNum, Integer pageSize);

    Res addComment(Comment comment);

}

