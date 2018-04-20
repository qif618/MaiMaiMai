package com.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lyf.entity.Notice;
import com.lyf.entity.PageBean;
public interface INoticeDao {
    /**
     * ���һ��Notice��¼
     * @param Notice
     * @return
     */
    int insertNotice(Notice Notice);

    /**
     * ���ID����Notice
     * @param id
     * @return
     */
    Notice findNoticeById(Integer id);
    /**
     * ���IDɾ��Notice
     * @param id
     * @return
     */
    int deleteNoticeById(Integer id);
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteNoticeById(List<Integer> list);

    /**
     * �����û�
     * @param Notice
     * @return
     */
    int updateNoticeById(Notice Notice);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param Notice
     * @return
     */
    int updateNoticeByIdSelective(Notice Notice);
    
    List<Notice> findNoticeList(@Param("title") String title , @Param("pageBean") PageBean pageBean);
    Long getNoticeCount(Notice notice);
}








