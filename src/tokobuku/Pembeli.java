package tokobuku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pembeli {
	private int idPembeli;
	private String nama;
	private String alamat;
	private String kota;
	
	public Pembeli() {}
	
	public Pembeli(int idPembeli, String nama, String alamat, String kota) {
		this.setIdPembeli(idPembeli);
		this.setNama(nama);
		this.setAlamat(alamat);
		this.setKota(kota);
	}

	public int getIdPembeli() {
		return idPembeli;
	}

	public void setIdPembeli(int idPembeli) {
		this.idPembeli = idPembeli;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}
	
	public void insert() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		
		PreparedStatement pStatement;

		String sql = "INSERT INTO pembeli(" + 
					"id, nama, "            +
					"alamat, kota) "        +
					"VALUES ('"             + 
					this.idPembeli + "','" + this.nama + "','" +
					this.alamat  + "','" + this.kota  + "')";
		
		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}
	
	public void update() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		
		PreparedStatement pStatement;

		String sql = "UPDATE pembeli SET " +
				"id = '"          + this.idPembeli    + "'," +
				" nama = '"      + this.nama     + "'," +
				" alamat = '"      + this.alamat     + "'," +
				" kota = '" + this.kota + "'";
		
		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}
	
	public void delete() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		
		PreparedStatement pStatement;

		String sql = "DELETE FROM pembeli WHERE id = '" + this.idPembeli + "'";
		
		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}
}
