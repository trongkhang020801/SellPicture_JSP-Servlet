package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author MyPC
 *
 */
public class DBConnect {
	public Connection cn;
	public void KetNoi() throws Exception{
		String hostName = "localhost";
		String database = "Web";
		String userName = "sa";
		String password = "tttl1209ntk0208";
		// Xac dinh he quan tri co so du lieu
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cn = DriverManager.getConnection("jdbc:sqlserver://TRONG-KHANG\\TRANTHITHULUYEN:1433;databaseName="+database+";user="+userName+";password="+password);
	}
	public static void main(String[] args) throws Exception {
		DBConnect  c = new DBConnect();
		c.KetNoi();
		System.out.println("ok");
	}

}
