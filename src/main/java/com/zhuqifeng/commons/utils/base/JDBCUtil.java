/**  
 * Project Name:JRJGService  
 * File Name:JDBCUtil.java  
 * Package Name:com.ffjr.commons.utils  
 * Date:2018年4月18日上午11:01:43  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.commons.utils.base;

/**  
 * ClassName:JDBCUtil <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2018年4月18日 上午11:01:43 <br/>  
 * @author   QiFeng.Zhu  
 * @version    1.0
 * @since    JDK 1.8  
 * @see        
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

	// 将获得的数据库与java的链接返回（返回的类型为Connection）
	public static Connection getConnection(String url, String user, String pwd) {
		Connection conn = null;
		try {
			// 1.加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获得数据库的连接
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
