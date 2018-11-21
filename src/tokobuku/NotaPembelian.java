package tokobuku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class NotaPembelian {
	private int idNota;
	private int jumlahBuku;
	private double hargaTotalperBuku;
	private String tanggalNota;
	
	private Pembeli pembeli;
	private Buku buku;
	
	public NotaPembelian() {
		pembeli = new Pembeli();
		buku = new Buku();
	}
	
	public int getIdNota() {
		return idNota;
	}

	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}

	public int getJumlahBuku() {
		return jumlahBuku;
	}

	public void setJumlahBuku(int jumlahBuku) {
		this.jumlahBuku = jumlahBuku;
	}

	public double getHargaTotalperBuku() {
		return hargaTotalperBuku;
	}

	public void setHargaTotalperBuku(double hargaTotalperBuku) {
		this.hargaTotalperBuku = hargaTotalperBuku;
	}
	
	public String getTanggalNota() {
		return tanggalNota;
	}

	public void setTanggalNota(String tanggalNota) {
		this.tanggalNota = tanggalNota;
	}

	public String getNamaPembeli() {
		return pembeli.getNama();
	}
	
	public void setNamaPembeli(String nama) {
		pembeli.setNama(nama);
	}
	
	public String getAlamatPembeli() {
		return pembeli.getAlamat();
	}
	
	public void setAlamatPembeli(String alamat) {
		pembeli.setAlamat(alamat);
	}
	
	public long getKodeBuku() {
		return buku.getIdBuku();
	}
	
	public void setKodeBuku(long kodeBuku) {
		buku.setIdBuku(kodeBuku);
	}
	
	public String getJudulBuku() {
		return buku.getJudul();
	}
	
	public void setJudulBuku(String judul) {
		buku.setJudul(judul);
	}
	
	public double getHargaBuku() {
		return buku.getHarga();
	}
	
	public void setHargaBuku(double harga) {
		buku.setHarga(harga);
	}
	
	public void insertHeader() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		
		PreparedStatement pStatement;

		String sql = "INSERT INTO header_nota(" + 
				"nama, alamat, "            +
				"tanggal)"            + 
				" VALUES ('"             + 
				getNamaPembeli() + "','" + getAlamatPembeli() + "','" +
				getTanggalNota() + "')";
		
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertDetail() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();
		
		PreparedStatement pStatement;
		
		//TODO ambil judul sama harga satuan dari tabel buku
		//TODO harga total = jumlah buku * harga satuan
		
		String sql = "INSERT INTO detail_nota(" + 
				"id_nota, judul, "            +
				"jumlah_buku, harga_satuan, "            +
				"harga_total)"            + 
				" VALUES ('"             + 
				getIdNota() + "','" + getJudulBuku() + "','" +
				getJumlahBuku() + "','" + getHargaBuku() + "','" +
				getHargaTotalperBuku() + "')";
		
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
