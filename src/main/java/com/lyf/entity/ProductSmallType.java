package com.lyf.entity;

import java.util.ArrayList;
import java.util.List;



public class ProductSmallType {

	private int id;
	private String name;
	private String remarks;
	private int bigTypeId;
	
	private ProductBigType bigType;
	private List<Product> productList=new ArrayList<Product>();
	
	
	public int getBigTypeId() {
		return bigTypeId;
	}
	public void setBigTypeId(int bigTypeId) {
		this.bigTypeId = bigTypeId;
	}
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
	
	public ProductBigType getBigType() {
		return bigType;
	}
	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	@Override
	public String toString() {
		return "ProductSmallType [id=" + id + ", name=" + name + ", remarks="
				+ remarks + ", bigTypeId=" + bigTypeId + ", bigType=" + bigType
				+ "]";
	}
	
	
}
