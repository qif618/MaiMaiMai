package com.lyf.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyf.entity.PageBean;
import com.lyf.entity.ProductBigType;
import com.lyf.service.ProductBigTypeService;
import com.lyf.service.ProductSmallTypeService;
import com.lyf.util.ResponseUtil;

@Controller
@RequestMapping("/productBigType.jhtml")
public class ProductBigTypeAction{

	@Resource
	private ProductBigTypeService productBigTypeService;
	
	@Resource
	private ProductSmallTypeService productSmallTypeService;
	
	@RequestMapping("/list.jhtml")
	public String list(HttpServletResponse response,int page,int rows,ProductBigType c_productBigType)throws Exception{
		PageBean pageBean=new PageBean(page,rows);
		List<ProductBigType> productBigTypeList=productBigTypeService.findProductBigTypeList(c_productBigType, pageBean);
		long total=productBigTypeService.getProductBigTypeCount(c_productBigType);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList","smallTypeList"});
		JSONArray resultRows=JSONArray.fromObject(productBigTypeList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", resultRows);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping("/save.jhtml")
	public String save(HttpServletResponse response,ProductBigType productBigType)throws Exception{
		productBigTypeService.saveProductBigType(productBigType);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/delete.jhtml")
	public String delete(HttpServletResponse response,String ids)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			if(productSmallTypeService.existSmallTypeWithBigTypeId(Integer.parseInt(idsStr[i]))){
				result.put("exist", "商品大类包含商品小类");
			}else{
				ProductBigType productBigType=productBigTypeService.getProductBigTypeById(Integer.parseInt(idsStr[i]));
				productBigTypeService.delete(productBigType);				
			}
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping("/comboList.jhtml")
	public String comboList(HttpServletResponse response)throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择...");
		jsonArray.add(jsonObject);
		List<ProductBigType> productBigTypeList=productBigTypeService.findAllBigTypeList();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList","smallTypeList"});
		JSONArray rows=JSONArray.fromObject(productBigTypeList, jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(response, jsonArray);
		return null;
	}

}
