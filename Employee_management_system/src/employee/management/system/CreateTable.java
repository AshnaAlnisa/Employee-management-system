package employee.management.system;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args)
	{
		try {
			Statement s = Conn.conn().createStatement();
			
			String loginTable = "create table login(username varchar(30), password varchar(30))";
			String employeeTable = "create table employee(name varchar(20), fname varchar(20), dob varchar(30), salary varchar(20), address varchar(100), phone varchar(20), email varchar(40), education varchar(20), designation varchar(30), aadhar varchar(25), empId varchar(15))";
//			s.execute(loginTable);
			s.execute(employeeTable);
			
			String insertQuery = "insert into login values('admin', 12345)";
//			s.executeUpdate(insertQuery);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
