package com.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lyf.entity.PageBean;
import com.lyf.entity.Tag;
public interface ITagDao {
    /**
     * ���һ��TAG��¼
     * @param tag
     * @return
     */
    int insertTag(Tag tag);

    List<Tag> findTagListByPage(@Param("name") String name,@Param("pageBean") PageBean pageBean);
    /**
     * ���ID����TAG
     * @param id
     * @return
     */
    Tag findTagById(Integer id);
    /**
     * ���IDɾ��TAG
     * @param id
     * @return
     */
    int deleteTagById(Integer id);
    /**
     * ���ID����ɾ��
     * @param list
     * @return
     */
    int batchDeleteTagById(List<Integer> list);

    /**
     * �����û�
     * @param Tag
     * @return
     */
    int updateTagById(Tag tag);
    /**
     * ���˵�ֵΪ��ʱ�������ֶ�
     * 
     * @param Tag
     * @return
     */
    int updateTagByIdSelective(Tag tag);
}








