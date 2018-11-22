package tokobuku;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NotaDAO {
	public NotaDAO() {
		
	}
	
	public ResultSet getDataNota() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();

		Statement statement;
		ResultSet resultSet;
		String sql = "SELECT * FROM header_nota";

		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		return resultSet;
	}
	
	public boolean isKodeNotaExist() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		Statement statement;
		ResultSet resultSet;
		
		String sql = "SELECT header_nota.id "
				+ "FROM header_nota "
				+ "WHERE header_nota.id "
				+ "AND EXISTS (SELECT detail_nota.id_nota "
				+ "FROM detail_nota WHERE header_nota.id = detail_nota.id_nota)";
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()){
			return true;
		}
		return false;
	}
}
