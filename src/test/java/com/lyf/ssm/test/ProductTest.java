package com.lyf.ssm.test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyf.dao.IProductDao;
import com.lyf.entity.Product;


public class ProductTest{
	 private ApplicationContext ac = null;  
	 IProductDao productDao = null;   
	    
		 @Before
		  public void before() {  
			 System.out.println("≥ı ºªØ");
		      ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml"); 
		      productDao = (IProductDao) ac.getBean("productDao");
		     
		  }  
		 
	    @Test  
	    public void findProductById() {  
	    	Product product = productDao.findProductById(8);
	    	System.out.println(product);
	    }  	  
		 
}













