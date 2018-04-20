package com.lyf.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyf.entity.Product;
import com.lyf.entity.ShoppingCart;
import com.lyf.entity.ShoppingCartItem;
import com.lyf.entity.User;
import com.lyf.service.ProductService;
import com.lyf.util.NavUtil;
import com.lyf.util.ResponseUtil;

@Controller
@RequestMapping("/shopping")
public class ShoppingAction{
	@Resource
	private ProductService productService;
	
	/**
	 * 增加购物车条目
	 * @param request
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addShoppingCartItem.jhtml")
	public void addShoppingCartItem(HttpServletRequest request,HttpServletResponse response,int productId)throws Exception{
		HttpSession session=request.getSession();
		Product product=productService.getProductById(productId);
		
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		if(shoppingCart==null){
			shoppingCart=new ShoppingCart();
			User currentUser=(User) session.getAttribute("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		
		boolean flag=true;
		for(ShoppingCartItem scI:shoppingCartItemList){
			if(scI.getProduct().getId()==product.getId()){
				scI.setCount(scI.getCount()+1);
				flag=false;
				break;
			}
		}
		if(flag){
			ShoppingCartItem shoppingCartItem=new ShoppingCartItem();
			shoppingCartItem.setProduct(product);
			shoppingCartItem.setCount(1);
			shoppingCartItemList.add(shoppingCartItem);
		}
		
		session.setAttribute("shoppingCart", shoppingCart);
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
	}
	@RequestMapping("/buy.jhtml")
	public String buy(HttpServletRequest request,HttpServletResponse response,int productId,Model model)throws Exception{
		HttpSession session=request.getSession();
		Product product=productService.getProductById(productId);
		
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		if(shoppingCart==null){
			shoppingCart=new ShoppingCart();
			User currentUser=(User) session.getAttribute("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		
		boolean flag=true;
		for(ShoppingCartItem scI:shoppingCartItemList){
			if(scI.getProduct().getId()==product.getId()){
				scI.setCount(scI.getCount()+1);
				flag=false;
				break;
			}
		}
		if(flag){
			ShoppingCartItem shoppingCartItem=new ShoppingCartItem();
			shoppingCartItem.setProduct(product);
			shoppingCartItem.setCount(1);
			shoppingCartItemList.add(shoppingCartItem);
		}
		
		session.setAttribute("shoppingCart", shoppingCart);
		
		String navCode=NavUtil.genNavCode("购物车");
		model.addAttribute("navCode", navCode);
		return "shopping/shopping";
	}
	/**
	 * 更新购物车条目
	 * @param request
	 * @param response
	 * @param productId
	 * @param count
	 * @throws Exception
	 */
	@RequestMapping("/updateShoppingCartItem.jhtml")
	public void updateShoppingCartItem(HttpServletRequest request,HttpServletResponse response,int productId,int count)throws Exception{
		HttpSession session=request.getSession();
		Product product=productService.getProductById(productId);
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		for(ShoppingCartItem scI:shoppingCartItemList){
			if(scI.getProduct().getId()==product.getId()){
				scI.setCount(count);
				break;
			}
		}
		
		session.setAttribute("shoppingCart", shoppingCart);
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		
	}
	/**
	 * 移除购物车条目
	 * @param request
	 * @param response
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/removeShoppingCartItem.jhtml")
	public String removeShoppingCartItem(HttpServletRequest request,HttpServletResponse response,int productId)throws Exception{
		HttpSession session=request.getSession();
		ShoppingCart shoppingCart=(ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		for(int i=0;i<shoppingCartItemList.size();i++){
			if(productId==shoppingCartItemList.get(i).getProduct().getId()){
				shoppingCartItemList.remove(i);
				break;
			}
		}
		shoppingCart.setShoppingCartItems(shoppingCartItemList);
		session.setAttribute("shoppingCart", shoppingCart);
		return "list";
	}
	/**
	 * 购物车列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list.jhtml")
	public String list(Model model)throws Exception{
		String navCode=NavUtil.genNavCode("购物车");
		model.addAttribute("navCode", navCode);
		return "shopping/shopping";
	}


}
