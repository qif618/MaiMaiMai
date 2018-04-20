package com.lyf.service;

import java.util.List;

import com.lyf.entity.Notice;
import com.lyf.entity.PageBean;

public interface NoticeService {

	public List<Notice> findNoticeList(Notice s_notice,PageBean pageBean);
	
	public Notice getNoticeById(int noticeId);
	
	public Long getNoticeCount(Notice s_notice);
	
	public void saveNotice(Notice notice);
	
	public void delete(Notice notice);
}
