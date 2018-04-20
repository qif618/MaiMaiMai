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

import com.lyf.entity.News;
import com.lyf.entity.PageBean;
import com.lyf.service.NewsService;
import com.lyf.util.NavUtil;
import com.lyf.util.ResponseUtil;

@Controller
public class NewsAction{

	@Resource
	private NewsService newsService;
	
	public String showNews(int newsId,Model model)throws Exception{
		News news=newsService.getNewsById(newsId);
		String navCode=NavUtil.genNavCode("新闻信息");
		model.addAttribute("news", news);
		model.addAttribute("navCode", navCode);
		return "news/newsDetails";
	}
	
	public String list(HttpServletResponse response,int page,int rows,News c_news)throws Exception{
		PageBean pageBean=new PageBean(page,rows);
		List<News> newsList=newsService.findNewsList(c_news, pageBean);
		long total=newsService.getNewsCount(c_news);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray resultRows=JSONArray.fromObject(newsList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", resultRows);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	public String save(HttpServletResponse response,News news)throws Exception{
		if(news.getId()==0){
			news.setCreateTime(new Date());
		}
		newsService.saveNews(news);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	public String delete(HttpServletResponse response,String ids)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			News news=newsService.getNewsById(Integer.parseInt(idsStr[i]));
			newsService.delete(news);
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}

}
