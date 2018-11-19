package tokobuku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	private Connection connection;
	
	static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://192.168.23.220:3306/tokobuku" + 
						"?useSSL=false&useJDBCCompliantTimezoneShift=true&use" +
						"LegacyDatetimeCode=false&serverTimezone=Asia/Jakarta";
	static String USER = "sofco";
	static String PASS = "s3234";

	private DatabaseConnection() {
		createConnection();
	}
	
	public void createConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Koneksi ke database gagal.");
		}
	}
	
	public Connection getConnection() {
        return connection;
    }
	
	public static DatabaseConnection getInstance() throws SQLException {
		if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
		return instance;
	}
}
