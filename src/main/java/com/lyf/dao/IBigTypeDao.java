package com.lyf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lyf.entity.ProductBigType;
public interface IBigTypeDao {
    /**
     * ���һ��BigType��¼
     * @param BigType
     * @return
     */
    int insertBigType(ProductBigType BigType);

    /**
     * ���ID����BigType
     * @param id
     * @return
     */
    ProductBigType findBigTypeById(Integer id);
    /**
     * ���IDɾ��BigType
     * @param id
     * @return
     */
    int deleteBigTypeById(Integer id);
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteBigTypeById(List<Integer> list);

    /**
     * �����û�
     * @param BigType
     * @return
     */
    int updateBigTypeById(ProductBigType BigType);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param BigType
     * @return
     */
    int updateBigTypeByIdSelective(ProductBigType BigType);
    
    List<ProductBigType> findAllBigTypeList();
}








