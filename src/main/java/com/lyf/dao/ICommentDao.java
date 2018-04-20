package com.lyf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lyf.entity.Comment;
public interface ICommentDao {
    /**
     * ���һ��Comment��¼
     * @param Comment
     * @return
     */
    int insertComment(Comment Comment);

    /**
     * ���ID����Comment
     * @param id
     * @return
     */
    Comment findCommentById(Integer id);
    /**
     * ���IDɾ��Comment
     * @param id
     * @return
     */
    int deleteCommentById(Integer id);
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteCommentById(List<Integer> list);

    /**
     * �����û�
     * @param Comment
     * @return
     */
    int updateCommentById(Comment Comment);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param Comment
     * @return
     */
    int updateCommentByIdSelective(Comment Comment);

	List<Comment> findCommentListByPage(Map<String, Object> map);

	Long getCommentCount(Comment s_Comment);
}








