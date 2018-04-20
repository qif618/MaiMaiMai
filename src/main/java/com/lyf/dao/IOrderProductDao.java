package com.lyf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lyf.entity.OrderProduct;
public interface IOrderProductDao {
    /**
     * ���һ���û���¼
     * @param user
     * @return
     */
    int insertOrderProduct(OrderProduct orderProduct);

    /**
     * ���ID���Ҷ���
     * @param id
     * @return
     */
    OrderProduct findOrderProductById(int id);
    /**
     * ���IDɾ��
     * @param id
     * @return
     */
    int deleteOrderProductById(int id);//����ϸ����ɾ����ϸ��
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteOrderProductById(List<Integer> list);

    /**
     * ���¶���
     * @param user
     * @return
     */
    int updateOrderProductById(OrderProduct orderProduct);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param user
     * @return
     */
    int updateOrderProductByIdSelective(OrderProduct orderProduct);
}








