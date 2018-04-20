package com.lyf.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyf.entity.PageBean;
import com.lyf.entity.Tag;
import com.lyf.service.TagService;
import com.lyf.util.ResponseUtil;

@Controller
@RequestMapping("/tag")
public class TagAction{
	@Resource
	private TagService tagService;
	/**
	 * 获取标签列表，分页
	 * @param page
	 * @param request
	 * @param response
	 * @param rows
	 * @param c_tag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list.jhtml")
	public String list(int page,HttpServletRequest request,HttpServletResponse response,int rows,Tag c_tag)throws Exception{
		PageBean pageBean=new PageBean(page,rows);
		List<Tag> tagList=tagService.findTagList(c_tag, pageBean);
		long total=tagService.getTagCount(c_tag);
		JSONArray resultRows=JSONArray.fromObject(tagList);
		JSONObject result=new JSONObject();
		result.put("rows", resultRows);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 保存标签
	 * @param tag
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save.jhtml")
	public String save(Tag tag,HttpServletResponse response)throws Exception{
		tagService.saveTag(tag);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 删除标签
	 * @param response
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete.jhtml")
	public String delete(HttpServletResponse response,String ids)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Tag tag=tagService.getTagById(Integer.parseInt(idsStr[i]));
			tagService.delete(tag);
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}

}
