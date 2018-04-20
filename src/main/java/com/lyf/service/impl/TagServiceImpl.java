package com.lyf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lyf.dao.ITagDao;
import com.lyf.entity.PageBean;
import com.lyf.entity.Tag;
import com.lyf.service.TagService;

@Service("tagService")
public class TagServiceImpl implements TagService{

	@Resource
	private ITagDao tagDao;
	
	@Override
	public List<Tag> findTagList(Tag s_tag,PageBean pageBean) {
		return tagDao.findTagListByPage(null,null);
	}

	@Override
	public Long getTagCount(Tag s_tag) {
		
		return null;
	}

	@Override
	public void saveTag(Tag tag) {
		tagDao.insertTag(tag);
	}

	@Override
	public void delete(Tag tag) {
		tagDao.deleteTagById(tag.getId());
	}

	@Override
	public Tag getTagById(int tagId) {
		return tagDao.findTagById(tagId);
	}

}
