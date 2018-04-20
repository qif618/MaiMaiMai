package com.lyf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyf.dao.IBigTypeDao;
import com.lyf.entity.PageBean;
import com.lyf.entity.ProductBigType;
import com.lyf.service.ProductBigTypeService;

@Service("productBigTypeService")
public class ProductBigTypeServiceImpl implements ProductBigTypeService{

	@Autowired
	private IBigTypeDao bigTypeDao;
	
	@Override
	public List<ProductBigType> findAllBigTypeList() {
		return bigTypeDao.findAllBigTypeList();
	}




	@Override
	public void saveProductBigType(ProductBigType productBigType) {
		// TODO Auto-generated method stub
		bigTypeDao.insertBigType(productBigType);
	}

	@Override
	public void delete(ProductBigType productBigType) {
		// TODO Auto-generated method stub
		bigTypeDao.deleteBigTypeById(productBigType.getId());
	}

	@Override
	public ProductBigType getProductBigTypeById(int id) {
		return bigTypeDao.findBigTypeById(id);
	}

	@Override
	public Long getProductBigTypeCount(ProductBigType s_productBigType) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<ProductBigType> findProductBigTypeList(
			ProductBigType s_productBigType, PageBean pageBean) {
		// TODO Auto-generated method stub
		return null;
	}

}
