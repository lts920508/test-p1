package cn.easybuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.easybuy.utils.EmptyUtils;

import com.mysql.jdbc.Statement;

public class BaseDaoImpl {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	public BaseDaoImpl(Connection connection) {
		this.connection = connection;
	}


	public ResultSet executeQuery(String sql,Object[] params){
		rs=null;
		try {
			ps=connection.prepareStatement(sql);
			if (!EmptyUtils.isEmpty(params)) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 新增记录
	 * @param sql
	 * @param params
	 * @return返回新增记录的主键值
	 */
	public int executeInsert(String sql,Object[] params) {
		Long id=0L;
		try {
			ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id=rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			id=null;
		}
		return id.intValue();
	}
	
	/**
	 * 修改删除和新增
	 * @param sql
	 * @param params
	 * @return 操作所影响的行数
	 */
	public int executeUpdate(String sql,Object[] params) {
		int updateRows=0;
		try {
			ps=connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			updateRows=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			updateRows=-1;
		}
		return updateRows;
	}
	
	
	
	public boolean closeResource(){
		if (null!=ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public boolean closeResource(ResultSet rs){
		if (null!=rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	
}
