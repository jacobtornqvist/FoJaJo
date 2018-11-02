package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	Connection con = null;
	LoginUtil login;
	
	public Connection createConnection() throws SQLException {
		return con = DriverManager.getConnection(login.getUrl(), login.getUsername(), login.getPassword());
	}


}
