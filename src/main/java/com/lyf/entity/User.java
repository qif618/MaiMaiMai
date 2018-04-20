package com.lyf.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User {
    private Integer id;

    private String address;

    private Date birthday;

    private String dentityCode;

    private String email;

    private String mobile;

    private String password;

    private String sex;

    private Integer status;

    private String trueName;

    private String userName;
    
    private List<Order> orderList=new ArrayList<Order>();

    
    public List<Order> getOrderList() {
		return orderList;
	}



	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}



	public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDentityCode() {
        return dentityCode;
    }

    public void setDentityCode(String dentityCode) {
        this.dentityCode = dentityCode == null ? null : dentityCode.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }



	@Override
	public String toString() {
		return "User [id=" + id + ", address=" + address + ", birthday="
				+ birthday + ", dentityCode=" + dentityCode + ", email="
				+ email + ", mobile=" + mobile + ", password=" + password
				+ ", sex=" + sex + ", status=" + status + ", trueName="
				+ trueName + ", userName=" + userName + ", orderList="
				+ orderList + "]";
	}
    
    
}