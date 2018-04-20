package com.lyf.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.lyf.entity.Notice;
import com.lyf.entity.PageBean;
import com.lyf.service.NoticeService;
import com.lyf.util.NavUtil;
import com.lyf.util.ResponseUtil;

@Controller
public class NoticeAction{
	@Resource
	private NoticeService noticeService;
	
	
	public String showNotice(int noticeId,Model model)throws Exception{
		Notice notice=noticeService.getNoticeById(noticeId);
		String navCode=NavUtil.genNavCode("公告信息");
		model.addAttribute("navCode", navCode);
		model.addAttribute("notice", notice);
		return "notice/noticeDetails";
	}
	
	public String list(HttpServletResponse response,int page,int rows,Notice c_notice)throws Exception{
		PageBean pageBean=new PageBean(page,rows);
		List<Notice> noticeList=noticeService.findNoticeList(c_notice, pageBean);
		long total=noticeService.getNoticeCount(c_notice);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray resultrows=JSONArray.fromObject(noticeList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", resultrows);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	public String save(HttpServletResponse response,Notice notice)throws Exception{
		if(notice.getId()==0){
			notice.setCreateTime(new Date());
		}
		noticeService.saveNotice(notice);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	public String delete(HttpServletResponse response,String ids)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Notice notice=noticeService.getNoticeById(Integer.parseInt(idsStr[i]));
			noticeService.delete(notice);
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}

}
