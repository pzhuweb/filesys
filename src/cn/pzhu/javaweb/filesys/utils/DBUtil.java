package cn.pzhu.javaweb.filesys.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	/*
	 * 1、完成数据库配置信息的读入
	 * 2、完成数据库驱动注册
	 * 3、完成获取连接的方法
	 * 4、释放资源的方法
	 */
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String password = null;
	static{
		InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * @return 数据库连接connection
	 */
	public static Connection getConnetion(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();	
			connection = null;
		}
		return connection;
	}
	
	/**
	 * 释放连接和静态命令对象
	 * @param con
	 * @param sta
	 */
	public static void release(Connection con, Statement sta){
		if (sta!=null) {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void release(Connection con, PreparedStatement sta){
		if (sta!=null) {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void release(Connection con, Statement sta, ResultSet res){
		if (res!=null) {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (sta!=null) {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void release(Connection con, PreparedStatement sta, ResultSet res){
		if (res!=null) {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (sta!=null) {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
