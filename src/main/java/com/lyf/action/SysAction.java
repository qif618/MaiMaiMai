package com.lyf.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyf.entity.News;
import com.lyf.entity.Notice;
import com.lyf.entity.PageBean;
import com.lyf.entity.Product;
import com.lyf.entity.ProductBigType;
import com.lyf.entity.Tag;
import com.lyf.service.NewsService;
import com.lyf.service.NoticeService;
import com.lyf.service.ProductBigTypeService;
import com.lyf.service.ProductService;
import com.lyf.service.TagService;
import com.lyf.util.ResponseUtil;

@Controller
@RequestMapping("/sys.jhtml")
public class SysAction{
	
	@Resource
	private ProductBigTypeService productBigTypeService;
	
	@Resource
	private TagService tagService;
	
	@Resource
	private NoticeService noticeService;
	
	@Resource
	private NewsService newsService;
	
	@Resource
	private ProductService productService;

	@RequestMapping("/refreshSystem.jhtml")
	public String refreshSystem(HttpServletResponse response,ServletContextEvent servletContextEvent)throws Exception{
		ServletContext application=servletContextEvent.getServletContext();
		List<ProductBigType> bigTypeList=productBigTypeService.findAllBigTypeList();
		application.setAttribute("bigTypeList", bigTypeList);
		
		List<Tag> tagList=tagService.findTagList(null,null);
		application.setAttribute("tagList", tagList);
		
		List<Notice> noticeList=noticeService.findNoticeList(null, new PageBean(1,7));
		application.setAttribute("noticeList", noticeList);
		
		List<News> newsList=newsService.findNewsList(null, new PageBean(1,7));
		application.setAttribute("newsList", newsList);
		
		Product c_product=new Product();
		c_product.setSpecialPrice(1);
		List<Product> specialPriceProductList=productService.findProductList(c_product, new PageBean(1,8));
		application.setAttribute("specialPriceProductList", specialPriceProductList);
		
		c_product=new Product();
		c_product.setHot(1);
		List<Product> hotProductList=productService.findProductList(c_product, new PageBean(1,6));
		application.setAttribute("hotProductList", hotProductList);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}


}
