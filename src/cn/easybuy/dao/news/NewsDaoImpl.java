package cn.easybuy.dao.news;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDaoImpl;
import cn.easybuy.entity.News;
import cn.easybuy.params.NewsParams;
import cn.easybuy.utils.EmptyUtils;

public class NewsDaoImpl extends BaseDaoImpl implements NewsDao {

	public NewsDaoImpl(Connection connection) {
		super(connection);
	}
	
	public News tableToClass(ResultSet rs) throws Exception{
		News news =new News();
		news.setId(rs.getInt("id"));
		news.setTitle(rs.getString("title"));
		news.setContent(rs.getString("content"));
		news.setCreateTime(rs.getString("createTime"));
		return news;
	}
	
	
	@Override
	public List<News> queryAllNews() throws Exception {
		List<News> newsList=new ArrayList<News>();
		StringBuffer sql=new StringBuffer("select id,title,content,createTime from easybuy_news ");
		sql.append(" limit 0,5");
		ResultSet rs=this.executeQuery(sql.toString(), null);
		while(rs.next()){
			News news=tableToClass(rs);
			newsList.add(news);
		}
		return newsList;
	}

	@Override
	public List<News> queryNewsList(NewsParams params) {
		List<Object> paramList =new ArrayList<Object>();
		List<News> newsList=new ArrayList<News>();
		StringBuffer sql=new StringBuffer("select id,title,content,createTime from easybuy_news where 1=1 ");
		if (EmptyUtils.isNotEmpty(params.getTitle())) {
			sql.append(" and title like ? ");
			paramList.add(params.getTitle());
		}
		if (EmptyUtils.isNotEmpty(params.getSort())) {
			sql.append(" order by "+params.getSort()+"  ");
		}
		if (EmptyUtils.isNotEmpty(params.isPage())) {
			sql.append(" limit "+params.getStartIndex()+","+params.getPageSize());
		}
		ResultSet rs=this.executeQuery(sql.toString(), paramList.toArray());
		try {
			while(rs.next()){
				News news=this.tableToClass(rs);
				newsList.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(rs);
			this.closeResource();
		}
		return newsList;
	}

	@Override
	public Integer queryNewsCount(NewsParams params) {
		int count=0;
		List<Object> paramList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer("select count(*) count from easybuy_news where 1=1 ");
		if (EmptyUtils.isNotEmpty(params.getTitle())) {
			sql.append(" and title like ? ");
			paramList.add(params.getTitle());
		}
		ResultSet rs=this.executeQuery(sql.toString(), paramList.toArray());
		try {
			while(rs.next()){
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeResource(rs);
			this.closeResource();
		}
		return count;
	}

	@Override
	public News getNewsById(Integer id) {
		String sql="select * from easybuy_news where id=? ";
		ResultSet rs=null;
		News news=null;
		try {
			Object[] param=new Object[]{ id };
			rs=this.executeQuery(sql, param);
			while (rs.next()) {
				news=this.tableToClass(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(rs);
			this.closeResource();
		}
		return news;
	}

	@Override
	public void addNews(News news) throws Exception {
		try {
			String sql="insert into easybuy_news(title,content,createTime) values(?,?,?) ";
			Object[] params=new Object[]{ news.getTitle(),news.getContent(),news.getCreateTime() };
			this.executeInsert(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
	}

	@Override
	public void deleteNews(int id) throws Exception {
		String sql="delete from easybuy_news WHERE id=?";
		Object[] params=new Object[]{ id };
		try {
			this.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
	}
}
