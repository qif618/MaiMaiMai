package com.lyf.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyf.entity.Comment;
import com.lyf.entity.PageBean;
import com.lyf.entity.Product;
import com.lyf.entity.ProductBigType;
import com.lyf.entity.ProductSmallType;
import com.lyf.service.CommentService;
import com.lyf.util.PageUtil;
import com.lyf.util.ResponseUtil;
import com.lyf.util.StringUtil;

@Controller
@RequestMapping("/comment")
public class CommentAction{
	@Resource
	private CommentService commentService;
	/**
	 * 获取评论列表
	 * @param request
	 * @param page
	 * @param comment
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list.jhtml")
	public String list(HttpServletRequest request,String page,Comment c_comment,Model model)throws Exception{
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<Comment> commentList=commentService.findCommentList(c_comment, pageBean);
		Long total=commentService.getCommentCount(c_comment);
		String pageCode=PageUtil.genPaginationNoParam(request.getContextPath()+"/comment/list.jhtml", total, Integer.parseInt(page), 3);
		model.addAttribute("commentList", commentList);
		model.addAttribute("pageCode", pageCode);
		return "comment";
	}
	@RequestMapping("/comment_list.jhtml")
	public void listComment(HttpServletResponse response,String page,String rows,Comment c_comment,Model model)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Comment> commentList=commentService.findCommentList(c_comment, pageBean);
		long total=commentService.getCommentCount(c_comment);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rowsResult=JSONArray.fromObject(commentList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rowsResult);
		result.put("total", total);
		ResponseUtil.write(response, result);
	}
	/**
	 * 保存评论
	 * @param response
	 * @param request
	 * @param comment
	 * @throws Exception
	 */
	@RequestMapping("/save.jhtml")
	public void save(HttpServletResponse response,HttpServletRequest request,Comment comment)throws Exception{
		if(comment.getCreateTime()==null){
			comment.setCreateTime(new Date());
		}
		commentService.saveComment(comment);
		request.getRequestDispatcher("/comment/comment_list.jhtml").forward(request,response);
	}
	/**
	 * 根据ID获取评论
	 * @param request
	 * @param response
	 * @param commentId
	 * @throws Exception
	 */
	@RequestMapping("/loadCommentById.jhtml")
	public void loadCommentById(HttpServletRequest request,HttpServletResponse response,String commentId)throws Exception{
		Comment comment=commentService.getCommentById(Integer.parseInt(commentId));
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONObject result=JSONObject.fromObject(comment, jsonConfig);
		ResponseUtil.write(response, result);
	}
	
	public void replay(HttpServletResponse response,Comment comment)throws Exception{
		comment.setReplyTime(new Date());
		commentService.saveComment(comment);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	/**
	 * 评论删除评论
	 * @param request
	 * @param response
	 * @param ids
	 * @throws Exception
	 */
	public void delete(HttpServletRequest request,HttpServletResponse response,String ids)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Comment comment=commentService.getCommentById(Integer.parseInt(idsStr[i]));
			commentService.delete(comment);
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	

}
