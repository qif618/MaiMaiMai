package com.lyf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lyf.entity.Order;
public interface IOrderDao {
    /**
     * ���һ���û���¼
     * @param user
     * @return
     */
    int insertOrder(Order order);

    /**
     * ���ID���Ҷ���
     * @param id
     * @return
     */
    Order findOrderById(int id);
    /**
     * ���IDɾ��
     * @param id
     * @return
     */
    int deleteOrderById(int id);//����ϸ����ɾ����ϸ��
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteOrderById(List<Integer> list);

    /**
     * ���¶���
     * @param user
     * @return
     */
    int updateOrderById(Order order);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param user
     * @return
     */
    int updateOrderByIdSelective(Order order);
    /**
     * ���������ҳ��ѯ
     * @param map
     * @return
     */
    List<Order> findOrderListByPage(Map<String,Object> map);
    
    /**
     * ���������ȡ��������
     * 
     */
    Long getOrderCount(Order order);
}








