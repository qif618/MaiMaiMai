package com.lyf.action;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyf.entity.PageBean;
import com.lyf.entity.Product;
import com.lyf.entity.ProductBigType;
import com.lyf.entity.ProductSmallType;
import com.lyf.service.ProductService;
import com.lyf.util.DateUtil;
import com.lyf.util.NavUtil;
import com.lyf.util.PageUtil;
import com.lyf.util.ResponseUtil;
import com.lyf.util.StringUtil;

@Controller
@RequestMapping("/product")
public class ProductAction{

	@Resource
	private ProductService productService;
	/**
	 * ��Ʒ�б�
	 * @param page
	 * @param model
	 * @param c_product
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list.jhtml")
	public String list(String page,Model model,Product c_product,HttpServletRequest request) throws Exception {
		if(c_product==null){
			c_product = new Product();
		}
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),8);
		List<Product> productList=productService.findProductList(c_product, pageBean);
		long total=productService.getProductCount(c_product);
		StringBuffer param=new StringBuffer();
		if(c_product!=null){
			if(c_product.getBigType()!=null){
				param.append("c_product.bigType.id="+c_product.getBigType().getId());
			}
			if(c_product.getSmallType()!=null){
				param.append("c_product.smallType.id="+c_product.getSmallType().getId());
			}
			if(StringUtil.isNotEmpty(c_product.getName())){
				param.append("c_product.name="+c_product.getName());
			}
		}
		String pageCode=PageUtil.genPagination(request.getContextPath()+"/product/list.jhtml", total, Integer.parseInt(page), 8, param.toString());
		String navCode=NavUtil.genNavCode("��Ʒ�б�");
		model.addAttribute("pageCode", pageCode);
		model.addAttribute("navCode", navCode);
		model.addAttribute("productList", productList);
		return "productList";
	}
	/**
	 * 商品详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showProduct.jhtml")
	public String showProduct(HttpServletRequest request,int productId,Model model)throws Exception{
		Product product=productService.getProductById(productId);
		System.out.println(product.getOrderProductList().toString());
		saveCurrentBrowse(request,product);
		String navCode=NavUtil.genNavCode("商品详情");
		model.addAttribute("navCode", navCode);
		model.addAttribute("product", product);
		model.addAttribute("mainPage", "/WEB-INF/jsp/product/productDetails.jsp");
		return "productMain";
	}
	
	private void saveCurrentBrowse(HttpServletRequest request,Product product)throws Exception{
		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
		List<Product> currentBrowseProduct=(List<Product>) session.getAttribute("currentBrowse");
		if(currentBrowseProduct==null){
			currentBrowseProduct=new LinkedList<Product>();
		}
		
		boolean flag=true;
		for(Product p:currentBrowseProduct){
			if(p.getId()==product.getId()){
				flag=false;
				break;
			}
		}
		
		if(flag){
			currentBrowseProduct.add(0,product);
		}
		
		if(currentBrowseProduct.size()==5){
			currentBrowseProduct.remove(4);
		}
		
		session.setAttribute("currentBrowse", currentBrowseProduct);
	}
	
	public String list(int page,int rows,Product c_product,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(page,rows);
		List<Product> productList=productService.findProductList(c_product, pageBean);
		long total=productService.getProductCount(c_product);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jsonConfig.registerJsonValueProcessor(ProductBigType.class,new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));
		jsonConfig.registerJsonValueProcessor(ProductSmallType.class,new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductSmallType.class));
		JSONArray resultRows=JSONArray.fromObject(productList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", resultRows);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	public String save(Product product,File proPic,String proPicFileName,ServletContextEvent servletContextEvent,HttpServletResponse response)throws Exception{
		if(proPic!=null){
			String imageName=DateUtil.getCurrentDateStr();
			String realPath=servletContextEvent.getServletContext().getRealPath("/images/product");
			String imageFile=imageName+"."+proPicFileName.split("\\.")[1];
			File saveFile=new File(realPath,imageFile);
			FileUtils.copyFile(proPic, saveFile);
			product.setProPic("images/product/"+imageFile);
		}else if(StringUtil.isEmpty(product.getProPic())){
			product.setProPic("");
		}
		productService.saveProduct(product);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	public String delete(HttpServletResponse response,String ids)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Product product=productService.getProductById(Integer.parseInt(idsStr[i]));
			productService.deleteProduct(product);
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	public String setProductWithHot(HttpServletResponse response,String ids)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.setProductWithHot(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	public String setProductWithSpecialPrice(HttpServletResponse response,String ids)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.setProductWithSpecialPrice(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
}
