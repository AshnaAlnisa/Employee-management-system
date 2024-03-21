package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	public static Connection conn()
	{
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/employeemanagementsystem";
			String username = "postgres";
			String password = "root";
			
			connection = DriverManager.getConnection(url, username, password);
//			System.out.println(connection);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}
	

}
