package com.lyf.service;

import java.util.List;

import com.lyf.entity.PageBean;
import com.lyf.entity.ProductSmallType;

public interface ProductSmallTypeService {

	public List<ProductSmallType> findProductSmallTypeList(ProductSmallType s_productSmallType,PageBean pageBean);
	
	public boolean existSmallTypeWithBigTypeId(int bigTypeId);
	
	public Long getProductSmallTypeCount(ProductSmallType s_productSmallType);
	
	public void saveProductSmallType(ProductSmallType productSmallType);
	
	public void delete(ProductSmallType productSmallType);
	
	public ProductSmallType getProductSmallTypeById(int id);
}
