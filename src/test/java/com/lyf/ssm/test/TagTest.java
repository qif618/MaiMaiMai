package com.lyf.ssm.test;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyf.dao.ITagDao;
import com.lyf.entity.PageBean;
import com.lyf.entity.Tag;


public class TagTest{
	 private ApplicationContext ac = null;  
	 ITagDao tagDao = null;   
	    
		 @Before
		  public void before() {  
			 System.out.println("≥ı ºªØ");
		      ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml"); 
		      tagDao = (ITagDao) ac.getBean("tagDao");
		     
		  }  
		 
	    @Test  
	    public void findById() {  
	    	Tag tag = new Tag();
	    	tag.setName("“Ù¿÷");
	    	PageBean pageBean = new PageBean(1, 4);
	    	@SuppressWarnings("unchecked")
			Map<String,Object> map = new HashedMap();
	    	map.put("tag", tag);
	    	map.put("pageBean", pageBean);
	    	List<Tag> list = tagDao.findTagListByPage(null,null);
	    	System.out.println(list.toString());
	    }  	  
}













