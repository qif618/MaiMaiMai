package com.lyf.ssm.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyf.dao.IUserDao;
import com.lyf.entity.PageBean;
import com.lyf.entity.User;


public class UserTest{
	 private ApplicationContext ac = null;  
	 IUserDao userDao = null;   
	    
		 @Before
		  public void before() {  
			 System.out.println("初始化");
		      ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml"); 
		      userDao = (IUserDao) ac.getBean("userDao");
		     
		  }  
	  
	    @Test  
	    public void findById() {  
	    	User user = userDao.findUserById(1);
	    	System.out.println(user.getOrderList().get(0).getOrderNo());
	    }  	     

	    @Test  
	    public void deleteById() {  
	    	int a = userDao.deleteUserById(10);
	    	System.out.println(a);
	    }  	   	
	    
	    @Test  
	    public void batchDeleteUserById() { 
	    	List<Integer> list = new ArrayList<>();
	    	list.add(10);
	    	list.add(11);
	    	int a = userDao.batchDeleteUserById(list);
	    	System.out.println(a);
	    }  	  
	    
	    @Test  
	    public void insertUser() { 
	    	User user = userDao.findUserById(1);
	    	userDao.insertUser(user);
	    }  	  
	    
	    @Test  
	    public void updateUserById() { 
	    	User user = userDao.findUserById(12);
	    	user.setAddress("大深圳贸联3333");
	    	userDao.updateUserById(user);
	    }  	  
	    
	    
	    @Test  
	    public void updateUserByIdSelective() { 
	    	User user = new User();
	    	user.setId(14);
	    	user.setAddress("BBBBBBBBBBBBB");
	    	userDao.updateUserByIdSelective(user);
	    }  	  
	    
	    @Test
	    public void findUserListByPage(){
	    	User user = new User();
	    	user.setUserName("jack");
	    	PageBean p = new PageBean(1, 3);
	    	Map<String,Object> map = new HashMap<>();
			map.put("user", user);
			map.put("pageBean", p);
	    	List<User> list = userDao.findUserListByPage(map);
	    	System.out.println(list.toString());
	    }
}













