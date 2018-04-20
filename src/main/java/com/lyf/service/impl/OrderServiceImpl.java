package com.lyf.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import com.lyf.dao.IOrderDao;
import com.lyf.entity.Order;
import com.lyf.entity.PageBean;
import com.lyf.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Resource
	private IOrderDao orderDao;
	
	@Override
	public void saveOrder(Order order) {
		if(orderDao.findOrderById(order.getId())==null){
			orderDao.insertOrder(order);
		}else{
			orderDao.updateOrderById(order);
		}
	}

	@Override
	public List<Order> findOrder(Order s_order, PageBean pageBean) {
		@SuppressWarnings("unchecked")
		Map<String,Object> map = new HashedMap();
		map.put("pageBean", pageBean);
    	map.put("order", s_order);
    	List<Order> list = orderDao.findOrderListByPage(map);
    	return list;
	}

	@Override
	public void updateOrderStatus(int status, String orderNo) {
		Order order = new Order();
		order.setStatus(status);
		order.setOrderNo(orderNo);
		orderDao.updateOrderByIdSelective(order);
	}

	@Override
	public Long getOrderCount(Order s_order) {
		Long total = orderDao.getOrderCount(s_order);
		return total;
	}

	@Override
	public Order getOrderById(int id) {
		return orderDao.findOrderById(id);
	}

}
