package cn.hr.utils;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 数据库连接池，帮你统一管理和分配你项目要用到的数据库连接
    形象的讲就是吧好多连接放在一个池子中，
   当外界请求时，池子给一个空闲连接，如果没有空闲则等待。
    这样系统消耗的资源比较少，不用来回关闭数据库连接，由连接池统一管理。
 * @author Administrator
 *
 */
public class JdbcUtils {
	
	private static DataSource ds = null;
	static{
		ds = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}
