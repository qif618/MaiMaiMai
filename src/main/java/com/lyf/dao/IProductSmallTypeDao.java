package com.lyf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lyf.entity.ProductSmallType;
public interface IProductSmallTypeDao {
    /**
     * ���һ��ProductSmallType��¼
     * @param ProductSmallType
     * @return
     */
    int insertProductSmallType(ProductSmallType ProductSmallType);

    /**
     * ���ID����ProductSmallType
     * @param id
     * @return
     */
    ProductSmallType findProductSmallTypeById(Integer id);
    /**
     * ���IDɾ��ProductSmallType
     * @param id
     * @return
     */
    int deleteProductSmallTypeById(Integer id);
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteProductSmallTypeById(List<Integer> list);

    /**
     * �����û�
     * @param ProductSmallType
     * @return
     */
    int updateProductSmallTypeById(ProductSmallType ProductSmallType);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param ProductSmallType
     * @return
     */
    int updateProductSmallTypeByIdSelective(ProductSmallType ProductSmallType);
}








