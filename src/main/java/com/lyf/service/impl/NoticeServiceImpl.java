package com.lyf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lyf.dao.INoticeDao;
import com.lyf.entity.Notice;
import com.lyf.entity.PageBean;
import com.lyf.service.NoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@Resource
	private INoticeDao noticeDao;
	
	@Override
	public List<Notice> findNoticeList(Notice s_notice, PageBean pageBean) {
		return noticeDao.findNoticeList(s_notice==null?null : s_notice.getTitle(),pageBean);
	}

	@Override
	public Notice getNoticeById(int noticeId) {
		return noticeDao.findNoticeById(noticeId);
	}

	@Override
	public Long getNoticeCount(Notice s_notice) {
		return noticeDao.getNoticeCount(s_notice);
	}

	@Override
	public void saveNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.insertNotice(notice);
	}

	@Override
	public void delete(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.deleteNoticeById(notice.getId());
	}

}
