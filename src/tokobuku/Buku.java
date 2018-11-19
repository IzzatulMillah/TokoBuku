package tokobuku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Buku {
	private long idBuku;
	private String judul;
	private int saldo;
	private double harga;
	private String flagAktif;
	
	public Buku() {}
	
	public Buku(long idBuku, String judul, int saldo, double harga, String flagAktif) {
		this.idBuku = idBuku;
		this.judul = judul;
		this.saldo = saldo;
		this.harga = harga;
		this.flagAktif = flagAktif;
	}
	
	public long getIdBuku() {
		return idBuku;
	}
	
	public void setIdBuku(long idBuku) {
		this.idBuku = idBuku;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getFlagAktif() {
		return flagAktif;
	}

	public void setFlagAktif(String flagAktif) {
		this.flagAktif = flagAktif;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public double getHarga() {
		return harga;
	}

	public void setHarga(double harga) {
		this.harga = harga;
	}
	
	public void insert() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		
		PreparedStatement pStatement;

		String sql = "INSERT INTO buku(" + 
				"id, judul, "            +
				"saldo, harga, "         +
				"flag_aktif)"            + 
				" VALUES ('"             + 
				this.idBuku + "','" + this.judul + "','" +
				this.saldo  + "','" + this.harga  + "','" + 
				this.flagAktif + "')";
		
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		
		PreparedStatement pStatement;

		String sql = "UPDATE buku SET " +
				"id = '"          + this.idBuku    + "'," +
				" judul = '"      + this.judul     + "'," +
				" saldo = '"      + this.saldo     + "'," +
				" harga = '"      + this.harga     + "'," +
				" flag_aktif = '" + this.flagAktif + "'";
		
		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}
	
	public void delete() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		
		PreparedStatement pStatement;

		String sql = "DELETE FROM buku WHERE id = '" + this.idBuku + "'";
		
		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}
}
