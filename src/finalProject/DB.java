package finalProject;

import java.sql.*;

public class DB {
	DB(){
		String jdbcUrl = "jdbc:mysql://mysql-localhost:3306/gabs_usc";
		String username = "superuser";
		String password = "password";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
