package com.lyf.entity;

import java.util.ArrayList;
import java.util.List;



public class ProductBigType {

	private int id;
	private String name;
	private String remarks;
	
	private List<Product> productList=new ArrayList<Product>();
	private List<ProductSmallType> smallTypeList=new ArrayList<ProductSmallType>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public List<ProductSmallType> getSmallTypeList() {
		return smallTypeList;
	}
	public void setSmallTypeList(List<ProductSmallType> smallTypeList) {
		this.smallTypeList = smallTypeList;
	}
	@Override
	public String toString() {
		return "ProductBigType [id=" + id + ", name=" + name + ", remarks="
				+ remarks + "]";
	}
	
	
	
}
