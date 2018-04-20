package com.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lyf.entity.News;
import com.lyf.entity.PageBean;
public interface INewsDao {
    /**
     * ���һ��News��¼
     * @param News
     * @return
     */
    int insertNews(News News);

    /**
     * ���ID����News
     * @param id
     * @return
     */
    News findNewsById(Integer id);
    /**
     * ���IDɾ��News
     * @param id
     * @return
     */
    int deleteNewsById(Integer id);
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteNewsById(List<Integer> list);

    /**
     * �����û�
     * @param News
     * @return
     */
    int updateNewsById(News News);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param News
     * @return
     */
    int updateNewsByIdSelective(News News);

	List<News> findNewsListByPage(@Param("title") String title,@Param("pageBean") PageBean pageBean);

	Long getNewsCount(News s_news);
}








