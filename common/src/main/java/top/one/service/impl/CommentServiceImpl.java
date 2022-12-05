package top.one.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import top.one.constants.SystemConstants;
import top.one.dao.CommentDao;
import top.one.domain.Res;
import top.one.domain.entity.Comment;
import top.one.domain.vo.CommentVo;
import top.one.domain.vo.PageVo;
import top.one.enums.AppHttpCodeEnum;
import top.one.exception.SystemException;
import top.one.service.CommentService;
import org.springframework.stereotype.Service;
import top.one.service.UserService;
import top.one.utils.BeanCopyUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-12-04 20:44:28
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    /**
     * 获取所有评论
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Res commentList(String commentType,Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper();
        //对id进行判断，如果是友链就不用加id直接获取
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        queryWrapper.eq(Comment::getRootId,-1);
        //分页查询
        Page page = new Page(pageNum, pageSize);
        page(page,queryWrapper);
        //获取所有根评论集合
        List<CommentVo> comments = toCommentVoList(page.getRecords());
        //获取所有根评论对应的子评论
        for (CommentVo commentVo : comments) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }
        return Res.okResult(new PageVo(comments,page.getTotal()));
    }

    /**
     * 根据根评论获取所有子评论
     * @param id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        //根据根评论查询所有子评论集合
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> list = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(list);
        return commentVos;

    }

    /**
     * 将评论转为VO
     * @param records
     * @return
     */
    private List<CommentVo> toCommentVoList(List<Comment> records) {
        List<CommentVo> commentsVo = BeanCopyUtils.copyBeanList(records, CommentVo.class);
        //遍历Vo集合
        for (CommentVo comment : commentsVo) {
            //根据id查询每条评论所属用户nickname
            String nickName = userService.getById(comment.getCreateBy()).getNickName();
            comment.setUsername(nickName);
            if (comment.getToCommentId()!= -1){
                //toCommentId不为1的查询所回复用户nickname
                String nickName1 = userService.getById(comment.getToCommentUserId()).getNickName();
                comment.setToCommentUserName(nickName1);
            }
        }
        return commentsVo;
    }

    /**
     * 添加评论接口
     * @param comment
     * @return
     */
    @Override
    public Res addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return Res.okResult();
    }
}

