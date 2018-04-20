package com.lyf.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import com.lyf.dao.ICommentDao;
import com.lyf.entity.Comment;
import com.lyf.entity.PageBean;
import com.lyf.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Resource
	private ICommentDao commentDao;
	
	@Override
	public List<Comment> findCommentList(Comment s_Comment, PageBean pageBean) {
		@SuppressWarnings("unchecked")
		Map<String,Object> map = new HashedMap();
		map.put("pageBean", pageBean);
    	map.put("s_Comment", s_Comment);
    	List<Comment> list = commentDao.findCommentListByPage(map);
    	return list;
	}

	@Override
	public Long getCommentCount(Comment s_Comment) {
		Long total = commentDao.getCommentCount(s_Comment);
		return total;
	}

	@Override
	public void saveComment(Comment comment) {
		if(commentDao.findCommentById(comment.getId())!=null){
			commentDao.updateCommentById(comment);
		}else{
			commentDao.insertComment(comment);
		}
	}

	@Override
	public Comment getCommentById(int commentId) {
		return commentDao.findCommentById(commentId);
	}

	@Override
	public void delete(Comment comment) {
		commentDao.deleteCommentById(comment.getId());
	}

}
