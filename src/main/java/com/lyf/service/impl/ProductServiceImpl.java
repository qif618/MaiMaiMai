package com.lyf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lyf.dao.IProductDao;
import com.lyf.entity.PageBean;
import com.lyf.entity.Product;
import com.lyf.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Resource
	private IProductDao productDAO;
	
	@Override
	public List<Product> findProductList(Product p, PageBean pageBean) {
		return productDAO.findProductList(p.getName(), pageBean);
	}

	@Override
	public Long getProductCount(Product s_product) {
		return productDAO.getProductCount(s_product);
	}

	@Override
	public Product getProductById(int productId) {
		return productDAO.findProductById(productId);
	}

	@Override
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		productDAO.insertProduct(product);
	}

	@Override
	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub
		productDAO.deleteProductById(product.getId());
	}

	@Override
	public void setProductWithHot(int productId) {
		//
	}

	@Override
	public void setProductWithSpecialPrice(int productId) {
		//
	}

	@Override
	public boolean existProductWithSmallTypeId(int smallTypeId) {
		return false;
	}

}
