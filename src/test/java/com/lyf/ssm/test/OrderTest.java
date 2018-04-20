package com.lyf.ssm.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyf.dao.IOrderDao;
import com.lyf.entity.Order;
import com.lyf.entity.PageBean;
import com.lyf.entity.User;


public class OrderTest{
	 private ApplicationContext ac = null;  
	 IOrderDao orderDao = null;   
	    
		 @Before
		  public void before() {  
			 System.out.println("≥ı ºªØ");
		      ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml"); 
		      orderDao = (IOrderDao) ac.getBean("orderDao");
		     
		  }  
		 
	    @Test  
	    public void findById() {  
	    	Order order = orderDao.findOrderById(1);
	    	System.out.println(order.getOrderProductList().get(0).getNum());
	    }  	  
		 
		@Test  
	    public void deleteOrderById() {  
	    	int a = orderDao.deleteOrderById(1);
	    	System.out.println(a);
	    }  	     
		@Test  
	    public void batchDeleteOrderById() { 
			List<Integer> list = new ArrayList<>();
			list.add(6);
			list.add(7);
	    	int a = orderDao.batchDeleteOrderById(list);
	    	System.out.println(a);
	    }  	   
		
		@Test  
	    public void insertOrder() { 
			Order order = new Order();
			order.setOrderNo("fdfdww");
			order.setUserId(1);
	    	int a = orderDao.insertOrder(order);
	    	System.out.println(a);
	    }  	   
		
		@Test  
	    public void updateOrderByIdSelective() { 
			Order order = new Order();
			order.setId(8);
			order.setCost(8888);
	    	int a = orderDao.updateOrderByIdSelective(order);
	    	System.out.println(a);
	    }  	   
		
		@Test  
	    public void updateOrderById() { 
			Order order = orderDao.findOrderById(1);
			order.setCost(99999);
	    	int a = orderDao.updateOrderById(order);
	    	System.out.println(a);
	    }  	  
	    
	    @SuppressWarnings("unchecked")
		@Test  
	    public void findOrderListByPage() { 
	    	Order order = new Order();
	    	order.setOrderNo("20171020042707");
	    	
	    	User user = new User();
	    	order.setUser(user);
	    	PageBean pageBean = new PageBean(1, 2);
	    	Map<String,Object> map = new HashedMap();
	    	map.put("pageBean", pageBean);
	    	map.put("order", order);
			List<Order> list = orderDao.findOrderListByPage(map);
	    	System.out.println(list.toString());
	    }  	   
	    
		@Test  
	    public void getOrderCount() { 
	    	Order order = new Order();
	    	order.setOrderNo("20171020042707");
	    	
	    	User user = new User();
	    	order.setUser(user);
			Long total = orderDao.getOrderCount(order);
	    	System.out.println(total.longValue());
	    }  	   
}













