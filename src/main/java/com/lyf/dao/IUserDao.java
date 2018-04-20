package com.lyf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lyf.entity.User;
public interface IUserDao {
    /**
     * ���һ���û���¼
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * ���ID�����û�
     * @param id
     * @return
     */
    User findUserById(Integer id);
    /**
     * ���IDɾ���û�
     * @param id
     * @return
     */
    int deleteUserById(Integer id);//����ϸ����ɾ����ϸ��
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteUserById(List<Integer> list);

    /**
     * �����û�
     * @param user
     * @return
     */
    int updateUserById(User user);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param user
     * @return
     */
    int updateUserByIdSelective(User user);

	int existUserWithUserName();

	List<User> login(User user);

	List<User> findUserListByPage(Map<String, Object> map);
}








