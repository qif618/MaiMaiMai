package com.lyf.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.lyf.entity.Order;
import com.lyf.entity.OrderProduct;
import com.lyf.entity.PageBean;
import com.lyf.entity.Product;
import com.lyf.entity.ShoppingCart;
import com.lyf.entity.ShoppingCartItem;
import com.lyf.entity.User;
import com.lyf.service.OrderService;
import com.lyf.util.DateUtil;
import com.lyf.util.NavUtil;
import com.lyf.util.ResponseUtil;
import com.lyf.util.StringUtil;

@Controller
public class OrderAction{
	
	private HttpServletRequest request;
	
	@Resource
	private OrderService orderService;

	public String save(Model model)throws Exception{
		HttpSession session=request.getSession();
		Order order=new Order();
		User currentUsre=(User)session.getAttribute("currentUser");
		order.setUser(currentUsre);
		order.setCreateTime(new Date());
		order.setOrderNo(DateUtil.getCurrentDateStr());
		order.setStatus(1);
		
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		
		float cost=0;
		List<OrderProduct> orderProductList=new LinkedList<OrderProduct>();
		for(ShoppingCartItem shoppingCartItem:shoppingCartItemList){
			Product product=shoppingCartItem.getProduct();
			OrderProduct orderProduct=new OrderProduct();
			orderProduct.setNum(shoppingCartItem.getCount());
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			cost+=product.getPrice()*shoppingCartItem.getCount();
			orderProductList.add(orderProduct);
		}
		order.setOrderProductList(orderProductList);
		order.setCost(cost);
		
		orderService.saveOrder(order);
		String navCode=NavUtil.genNavCode("购物");
		model.addAttribute("navCode", navCode);
		session.removeAttribute("shoppingCart");
		return "shopping/shopping-result";
	}
	
	public String findOrder(Order c_order,Model model)throws Exception{
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		c_order.setUser(currentUser);
		List<Order> orderList=orderService.findOrder(c_order, null);
		String navCode=NavUtil.genNavCode("个人中心");
		model.addAttribute("orderList", orderList);
		model.addAttribute("navCode", navCode);
		return "userCenter/orderList";
	}
	
	public String confirmReceive(HttpServletResponse response,int status,String orderNo)throws Exception{
		orderService.updateOrderStatus(status, orderNo);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	public String list(HttpServletResponse response,int page,int rows,Order c_order)throws Exception{
		PageBean pageBean=new PageBean(page,rows);
		List<Order> orderList=orderService.findOrder(c_order, pageBean);
		long total=orderService.getOrderCount(c_order);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		jsonConfig.registerJsonValueProcessor(User.class,new ObjectJsonValueProcessor(new String[]{"id","userName"}, User.class));
		JSONArray resultrows=JSONArray.fromObject(orderList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", resultrows);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	public String findProductListByOrderId(HttpServletResponse response,String id)throws Exception{
		if(StringUtil.isEmpty(id)){
			return null;
		}
		Order order=orderService.getOrderById(Integer.parseInt(id));
		List<OrderProduct> orderProductList=order.getOrderProductList();
		JSONObject result=new JSONObject();
		JSONArray rows=new JSONArray();
		for(OrderProduct orderProduct:orderProductList){
			Product product=orderProduct.getProduct();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("productName", product.getName());
			jsonObject.put("proPic", product.getProPic());
			jsonObject.put("price", product.getPrice());
			jsonObject.put("num", orderProduct.getNum());
			jsonObject.put("subtotal", product.getPrice()*orderProduct.getNum());
			rows.add(jsonObject);
		}
		result.put("rows", rows);
		result.put("total", rows.size());
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	public String modifyOrderStatus(HttpServletResponse response,String orderNos,int status)throws Exception{
		String []orderNoStr=orderNos.split(",");
		for(int i=0;i<orderNoStr.length;i++){
			orderService.updateOrderStatus(status, orderNoStr[i]);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
}
