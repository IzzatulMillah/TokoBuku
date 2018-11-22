package tokobuku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotaPembelian {
	private int idNota;
	private int jumlahBuku;
	private double hargaTotalperBuku;
	private String tanggalNota;

	private Pembeli pembeli;
	private Buku buku;

	BukuDao bukuDao = new BukuDao();

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

	public String getKotaPembeli() {
		return pembeli.getKota();
	}

	public void setKotaPembeli(String kota) {
		pembeli.setKota(kota);
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
				"kota, tanggal)"            + 
				" VALUES ('"             + 
				getNamaPembeli() + "','" + getAlamatPembeli() + "','" +
				getKotaPembeli() + "','" + getTanggalNota() + "')";

		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}

	public void insertDetail() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();

		PreparedStatement pStatement;
		ResultSet resultSet;
		String judul = null;
		double harga = 0;

		resultSet = bukuDao.getDataBuku(getKodeBuku());
		while(resultSet.next()) {
			judul = resultSet.getString("judul");
			harga = resultSet.getDouble("harga");
		}

		double total = harga * this.jumlahBuku;

		String sql = "INSERT INTO detail_nota(" + 
				"id_nota, judul, "              +
				"jumlah_buku, harga_satuan, "   +
				"harga_total)"                  + 
				" VALUES ('"                    + 
				getIdNota()     + "','" + judul + "','" +
				getJumlahBuku() + "','" + harga + "','" +
				total + "')";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateHeader() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();

		PreparedStatement pStatement;

		String sql = "UPDATE header_nota SET " +
				"id = '"       + getIdNota()        + "'," +
				" nama = '"    + getNamaPembeli()   + "'," +
				" alamat = '"  + getAlamatPembeli() + "'," +
				" tanggal = '" + getTanggalNota()   + "'";

		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}

	public void updateDetail() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();

		PreparedStatement pStatement;
		ResultSet resultSet;
		String judul = null;
		double harga = 0;

		resultSet = bukuDao.getDataBuku(getKodeBuku());
		while(resultSet.next()) {
			judul = resultSet.getString("judul");
			harga = resultSet.getDouble("harga");
		}

		double total = harga * this.jumlahBuku;

		String sql = "UPDATE detail_nota SET " +
				"id_nota = '"       + getIdNota()     + "'," +
				" judul = '"        + judul           + "'," +
				" jumlah_buku = '"  + getJumlahBuku() + "'," +
				" harga_satuan = '" + harga           + "'," +
				" harga_total = '"  + total           + "'";

		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}

	public void deleteHeader() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();

		PreparedStatement pStatement;

		String sql = "DELETE FROM header_nota WHERE id = '" + getIdNota() + "'";

		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}

	public void deleteDetail() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();

		PreparedStatement pStatement;

		String sql = "DELETE FROM detail_nota WHERE id = '" + getIdNota() + "'";

		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}
}
