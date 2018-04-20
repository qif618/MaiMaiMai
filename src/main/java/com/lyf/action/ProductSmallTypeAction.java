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
import com.lyf.entity.ProductSmallType;
import com.lyf.service.ProductService;
import com.lyf.service.ProductSmallTypeService;
import com.lyf.util.ResponseUtil;

@Controller
@RequestMapping("/productSmallType")
public class ProductSmallTypeAction{

	@Resource
	private ProductSmallTypeService productSmallTypeService;
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("/list.jhtml")
	public String list(HttpServletResponse response,int page,int rows,ProductSmallType c_productSmallType)throws Exception{
		PageBean pageBean=new PageBean(page,rows);
		List<ProductSmallType> productSmallTypeList=productSmallTypeService.findProductSmallTypeList(c_productSmallType, pageBean);
		long total=productSmallTypeService.getProductSmallTypeCount(c_productSmallType);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList"});
		jsonConfig.registerJsonValueProcessor(ProductBigType.class,new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));
		JSONArray resultRows=JSONArray.fromObject(productSmallTypeList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", resultRows);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping("/save.jhtml")
	public String save(HttpServletResponse response,ProductSmallType productSmallType)throws Exception{
		productSmallTypeService.saveProductSmallType(productSmallType);
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
			if(productService.existProductWithSmallTypeId(Integer.parseInt(idsStr[i]))){
				result.put("exist", "商品小类包含商品");
			}else{
				ProductSmallType productSmallType=productSmallTypeService.getProductSmallTypeById(Integer.parseInt(idsStr[i]));
				productSmallTypeService.delete(productSmallType);				
			}
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping("/comboList.jhtml")
	public String comboList(HttpServletResponse response,ProductSmallType c_productSmallType)throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择...");
		jsonArray.add(jsonObject);
		List<ProductSmallType> productSmallTypeList=productSmallTypeService.findProductSmallTypeList(c_productSmallType,null);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"bigType","productList"});
		JSONArray rows=JSONArray.fromObject(productSmallTypeList, jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(response, jsonArray);
		return null;
	}

}
