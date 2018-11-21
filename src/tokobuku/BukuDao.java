package tokobuku;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BukuDao {
	
	public BukuDao() {
		
	}
	
	public List<Buku> getAllBuku() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		List<Buku> listBuku = new ArrayList<>();
		
		Statement statement;
		ResultSet resultSet;
		String sql = "SELECT * FROM buku";
		
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()){
			long idBuku = resultSet.getLong("id");
			String judul = resultSet.getString("judul");
			String tipeBuku = resultSet.getString("tipe_buku");
			int saldo = resultSet.getInt("saldo");
			double harga = resultSet.getDouble("harga");
			String flagAktif = resultSet.getString("flag_aktif");

			listBuku.add(new Buku(idBuku, judul, tipeBuku, saldo, harga, flagAktif));
		}
		return listBuku;
	}
	
	public Buku getDataBuku(long kodeBuku) throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		
		Statement statement;
		ResultSet resultSet;
		String sql = "SELECT judul, harga FROM buku WHERE id ='" + kodeBuku + "'";
		
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			String judul = resultSet.getString("judul");
			double harga = resultSet.getDouble("harga");
			int saldo = resultSet.getInt("saldo");
			
			return new Buku(0, judul, null, saldo, harga, null);
		}
		return null;
	}
}
