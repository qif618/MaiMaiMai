package com.lyf.service;

import java.util.List;

import com.lyf.entity.Comment;
import com.lyf.entity.PageBean;

public interface CommentService {

	public List<Comment> findCommentList(Comment s_Comment,PageBean pageBean);
	
	public Long getCommentCount(Comment s_Comment);
	
	public void saveComment(Comment comment);
	
	public Comment getCommentById(int commentId);
	
	public void delete(Comment comment);
}
