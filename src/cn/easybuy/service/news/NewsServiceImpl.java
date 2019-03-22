package cn.easybuy.service.news;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.news.NewsDao;
import cn.easybuy.dao.news.NewsDaoImpl;
import cn.easybuy.entity.News;
import cn.easybuy.params.NewsParams;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.Pager;

public class NewsServiceImpl implements NewsService {
	private Connection connection;
	private NewsDao newsDao;

	@Override
	public List<News> queryAllNews() {
		List<News> newsList = new ArrayList<News>();
		try {
			connection = DataSourceUtil.openConnection();
			newsDao = new NewsDaoImpl(connection);
			newsList = newsDao.queryAllNews();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return newsList;
	}

	@Override
	public List<News> queryNewsList(Pager pager) {
		List<News> newsList = new ArrayList<News>();
		try {
			connection = DataSourceUtil.openConnection();
			newsDao = new NewsDaoImpl(connection);
			NewsParams params = new NewsParams();
			params.openPage(
					(pager.getCurrentPage() - 1) * pager.getRowPerPage(),
					pager.getRowPerPage());
			newsList = newsDao.queryNewsList(params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return newsList;
	}

	@Override
	public Integer queryNewsCount(NewsParams params) {
		int count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			newsDao = new NewsDaoImpl(connection);
			count = newsDao.queryNewsCount(params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return count;
	}

	@Override
	public News getNewsById(Integer id) {
		News news = null;
		try {
			connection = DataSourceUtil.openConnection();
			newsDao = new NewsDaoImpl(connection);
			news = newsDao.getNewsById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return news;
	}

	@Override
	public void addNews(News news) {
		try {
			connection = DataSourceUtil.openConnection();
			newsDao = new NewsDaoImpl(connection);
			newsDao.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
	}

	@Override
	public void deleteNews(int id) {
		try {
			connection = DataSourceUtil.openConnection();
			newsDao = new NewsDaoImpl(connection);
			newsDao.deleteNews(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
	}
}