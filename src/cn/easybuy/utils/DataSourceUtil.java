package cn.easybuy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataSourceUtil {

	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	static{
		init();
	}
	public static void init(){
		Properties params=new Properties();
		String 	configFile="database.properties";
		InputStream is=DataSourceUtil.class.getClassLoader().getResourceAsStream(configFile);
		try {
			params.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver=params.getProperty("driver");
		url=params.getProperty("url");
		user=params.getProperty("username");
		password=params.getProperty("password");
		
	}
	
	//获取链接
	public static Connection openConnection() throws SQLException{
		Connection connection=null;
		try {
			Class.forName(driver);
			connection=DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return connection;
	}
	
	//关闭连接
	public static void closeConnection(Connection connection){
		if(null!=connection){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
