package com.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lyf.entity.PageBean;
import com.lyf.entity.Product;
public interface IProductDao {
    /**
     * ���һ����Ʒ
     * @param user
     * @return
     */
    int insertProduct(Product product);

    /**
     * ���ID������Ʒ
     * @param id
     * @return
     */
    Product findProductById(int id);
    /**
     * ���IDɾ��
     * @param id
     * @return
     */
    int deleteProductById(int id);//����ϸ����ɾ����ϸ��
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteProductById(List<Integer> list);

    /**
     * ������Ʒ��Ϣ
     * @param user
     * @return
     */
    int updateProductById(Product product);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param user
     * @return
     */
    int updateProductByIdSelective(Product product);
     
    List<Product> findProductList(@Param("name") String name,
    							
    							@Param("pageBean") PageBean pageBean);
    
    Long getProductCount(Product product);
}








