package DBUtil;

import java.sql.*;
import java.util.Hashtable;

public class Oracle_DB {
	/**
	 * 一个非常标准的连接Oracle数据库的示例代码
	 */
	public static Hashtable testOracle(String table_name) {
		Hashtable ht = new Hashtable();
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet rs = null;// 创建一个结果集对象
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
			System.out.println("开始尝试连接数据库！");
			String url = "jdbc:oracle:thin:@192.168.1.240:1521:posp";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
			String user = "aimOra";// 用户名,系统默认的账户名
			String password = "aimOra";// 你安装时选设置的密码
			con = DriverManager.getConnection(url, user, password);// 获取连接
			System.out.println("连接成功！");
			String sql = "select   lower(column_name)  as col_name, upper(substr(column_name,0,1))||lower(substr(column_name,2,length(column_name))) as col_fun  from   user_tab_columns   where   table_name   =   upper(?)";// 预编译语句，“？”代表参数
			pre = con.prepareStatement(sql);// 实例化预编译语句
			pre.setString(1, table_name);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			rs = pre.executeQuery();// 执行查询，注意括号中不需要再加参数

			while (rs.next()) {
				ht.put(rs.getString("col_name"), rs.getString("col_fun"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ht;
	}
}
