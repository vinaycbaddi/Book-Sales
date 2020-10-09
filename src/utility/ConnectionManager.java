package utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {
	
//	Method to get properties from the jdbc.properties file
	public static Properties loadPropertiesFile() throws IOException {
		Properties prop = new Properties();
		InputStream in = ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		prop.load(in);
		in.close();
		return prop;
	}

//	Method to load the properties and establish connection with database
	public static Connection getConnection() throws IOException {
		Connection con = null;
		Properties newprop = new Properties();
		newprop = loadPropertiesFile();
		String driver = newprop.getProperty("driver");
		String url = newprop.getProperty("url");
		String username = newprop.getProperty("username");
		String password = newprop.getProperty("password");
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			if (con != null) {
				System.out.print("");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
}
