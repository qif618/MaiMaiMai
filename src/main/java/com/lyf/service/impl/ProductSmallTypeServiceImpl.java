package com.lyf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lyf.dao.IProductSmallTypeDao;
import com.lyf.entity.PageBean;
import com.lyf.entity.ProductSmallType;
import com.lyf.service.ProductSmallTypeService;

@Service("productSmallTypeService")
public class ProductSmallTypeServiceImpl implements ProductSmallTypeService{

	@Resource
	private IProductSmallTypeDao smallTypeDao;
	
	@Override
	public List<ProductSmallType> findProductSmallTypeList(
			ProductSmallType s_productSmallType,PageBean pageBean) {
		return null;
	}

	@Override
	public boolean existSmallTypeWithBigTypeId(int bigTypeId) {
		return false;
	}

	@Override
	public Long getProductSmallTypeCount(ProductSmallType s_productSmallType) {
		return null;
	}

	@Override
	public void saveProductSmallType(ProductSmallType productSmallType) {
		// TODO Auto-generated method stub
		smallTypeDao.insertProductSmallType(productSmallType);
	}

	@Override
	public void delete(ProductSmallType productSmallType) {
		// TODO Auto-generated method stub
		smallTypeDao.deleteProductSmallTypeById(productSmallType.getId());
	}

	@Override
	public ProductSmallType getProductSmallTypeById(int id) {
		return smallTypeDao.findProductSmallTypeById(id);
	}

}
