package com.lyf.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import com.lyf.dao.INewsDao;
import com.lyf.entity.News;
import com.lyf.entity.PageBean;
import com.lyf.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService{

	@Resource
	private INewsDao newsDao;
	
	@Override
	public List<News> findNewsList(News s_news, PageBean pageBean) {
    	List<News> list = newsDao.findNewsListByPage(s_news==null ? null : s_news.getTitle(),pageBean);
    	return list;
	}

	@Override
	public News getNewsById(int newsId) {
		return newsDao.findNewsById(newsId);
	}

	@Override
	public Long getNewsCount(News s_news) {
		Long total = newsDao.getNewsCount(s_news);
		return total;
	}

	@Override
	public void saveNews(News news) {
		if(newsDao.findNewsById(news.getId())==null){
			newsDao.insertNews(news);
		}else{
			newsDao.updateNewsById(news);
		}
	}

	@Override
	public void delete(News news) {
		newsDao.deleteNewsById(news.getId());
	}

}
