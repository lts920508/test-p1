package cn.easybuy.dao.news;

import java.util.List;

import cn.easybuy.entity.News;
import cn.easybuy.params.NewsParams;
import cn.easybuy.utils.Pager;
/**
 * 查询首页中的新闻资讯
 * @author 0
 * @params
 * @return
 */
public interface NewsDao {
	public List<News> queryAllNews() throws Exception;
	public List<News> queryNewsList (NewsParams params);
	public Integer queryNewsCount(NewsParams params);
	public News getNewsById(Integer id);
	public void addNews(News news) throws Exception;
	public void deleteNews(int id) throws Exception;
}
