package com.lyf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lyf.dao.IUserDao;
import com.lyf.entity.PageBean;
import com.lyf.entity.User;
import com.lyf.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private IUserDao userDao;
	
	@Override
	public void saveUser(User user) {
		if(userDao.findUserById(user.getId())!=null){
			userDao.updateUserById(user);
		}else{
			userDao.insertUser(user);
		}
	}

	@Override
	public boolean existUserWithUserName(String userName) {
		int count = userDao.existUserWithUserName();
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public User login(User user) {
		List<User> list = userDao.login(user);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<User> findUserList(User s_user, PageBean pageBean) {
		Map<String,Object> map = new HashMap<>();
		map.put("user", s_user);
		map.put("pageBean", pageBean);
		List<User> list = userDao.findUserListByPage(map);
		return null;
	}

	@Override
	public Long getUserCount(User s_user) {
		return null;
	}

	@Override
	public void delete(User user) {
	}

	@Override
	public User getUserById(int id) {
		return null;
	}

}
