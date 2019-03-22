package cn.easybuy.service.news;

import java.util.List;

import cn.easybuy.entity.News;
import cn.easybuy.params.NewsParams;
import cn.easybuy.utils.Pager;

public interface NewsService {
	/**
	 * 查询首页中的新闻资讯
	 * @author 0
	 * @params
	 * @return
	 */
		public List<News> queryAllNews();
		public List<News> queryNewsList (Pager pager);
		public Integer queryNewsCount(NewsParams params);
		public News getNewsById(Integer id);
		public void addNews(News news);
		public void deleteNews(int id);
}
