package tokobuku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import tokobuku.Buku.Diskon;

public class NotaPembelian {
	private int idNota;
	private String tanggalNota;
	private String namaKasir;
	private double totalHargaSemuaBuku;
	private double diskonPerMember;
	private double totalHargaAkhir;
	private String jenisPembayaran;
	private double jumlahPembayaran;
	private double kembalian;
	
	private int jumlahBuku;
	private double diskonPerBuku;
	private double hargaTotalperBuku;

	private Pembeli pembeli;
	private Buku buku;
	private List<Buku> listBuku;

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
	
	public String getTanggalNota() {
		return tanggalNota;
	}

	public void setTanggalNota(String tanggalNota) {
		this.tanggalNota = tanggalNota;
	}

	public String getNamaKasir() {
		return namaKasir;
	}

	public void setNamaKasir(String namaKasir) {
		this.namaKasir = namaKasir;
	}

	public double getTotalHargaSemuaBuku() {
		return totalHargaSemuaBuku;
	}

	public void setTotalHargaSemuaBuku(double totalHargaSemuaBuku) {
		this.totalHargaSemuaBuku = totalHargaSemuaBuku;
	}

	public double getDiskonPerMember() {
		return diskonPerMember;
	}

	public void setDiskonPerMember(double diskonPerMember) {
		this.diskonPerMember = diskonPerMember;
	}

	public double getTotalHargaAkhir() {
		return totalHargaAkhir;
	}

	public void setTotalHargaAkhir(double totalHargaAkhir) {
		this.totalHargaAkhir = totalHargaAkhir;
	}
	
	public String getJenisPembayaran() {
		return jenisPembayaran;
	}

	public void setJenisPembayaran(String jenisPembayaran) {
		this.jenisPembayaran = jenisPembayaran;
	}

	public double getJumlahPembayaran() {
		return jumlahPembayaran;
	}

	public void setJumlahPembayaran(double jumlahPembayaran) {
		this.jumlahPembayaran = jumlahPembayaran;
	}

	public double getKembalian() {
		return kembalian;
	}

	public void setKembalian(double kembalian) {
		this.kembalian = kembalian;
	}

	public int getJumlahBuku() {
		return jumlahBuku;
	}

	public void setJumlahBuku(int jumlahBuku) {
		this.jumlahBuku = jumlahBuku;
	}
	
	public double getDiskonPerBuku() {
		return diskonPerBuku;
	}

	public void setDiskonPerBuku(double diskonPerBuku) {
		this.diskonPerBuku = diskonPerBuku;
	}

	public double getHargaTotalperBuku() {
		return hargaTotalperBuku;
	}

	public void setHargaTotalperBuku(double hargaTotalperBuku) {
		this.hargaTotalperBuku = hargaTotalperBuku;
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
				"nama, alamat, "                +
				"kota, tanggal, "               +
				"kasir, total_harga, "          +
				"diskon, total_akhir, "         +
				"jenis_bayar, jumlah_bayar, "   +
				"kembalian)"             + 
				" VALUES ('"             + 
				getNamaPembeli() + "','" + getAlamatPembeli() + "','" +
				getKotaPembeli() + "','" + getTanggalNota() + "','" +
				getNamaKasir()   + "','" + getTotalHargaSemuaBuku() + "','" +
				getDiskonPerMember() + "','" + getTotalHargaAkhir() + "','" +
				getJenisPembayaran() + "','" + getJumlahPembayaran() + "','" +
				getKembalian() + "')";

		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}
	
	Diskon diskon = (int jumlahBuku, double hargaBuku, double prosenDiskonPerBuku) 
			-> ((jumlahBuku * hargaBuku * prosenDiskonPerBuku) / 0.01);

	public void insertDetail() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();

		PreparedStatement pStatement;
		ResultSet resultSet;

		resultSet = bukuDao.getDataBuku(getKodeBuku());
		while(resultSet.next()) {
			setJudulBuku(resultSet.getString("judul"));
			setHargaBuku(resultSet.getDouble("harga"));
		}
		
		this.diskonPerBuku = buku.hitungDiskon(getJumlahBuku(), getHargaBuku(), getDiskonPerBuku(), diskon);

		this.hargaTotalperBuku = getHargaBuku() * this.jumlahBuku;

		String sql = "INSERT INTO detail_nota(" + 
				"id_nota, judul, "              +
				"jumlah_buku, harga_satuan, "   +
				"diskon, harga_total)"          + 
				" VALUES ('"                    + 
				getIdNota()     + "','" + getJudulBuku() + "','" +
				getJumlahBuku() + "','" + getHargaBuku() + "','" +
				getDiskonPerBuku() + "','" + this.hargaTotalperBuku + "')";
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
				" kota = '"    + getKotaPembeli()   + "'," +
				" tanggal = '"  + getTanggalNota() + "'," +
				" kasir = '"    + getNamaKasir()   + "'," +
				" total_harga = '"  + getTotalHargaSemuaBuku() + "'," +
				" diskon = '"    + getDiskonPerMember()   + "'," +
				" total_akhir = '"  + getTotalHargaAkhir() + "'," +
				" jenis_bayar = '"    + getJenisPembayaran()   + "'," +
				" jumlah_bayar = '"  + getJumlahPembayaran() + "'," +
				" kembalian = '" + getKembalian()   + "'";

		pStatement = connection.prepareStatement(sql);
		pStatement.execute();
	}

	public void updateDetail() throws SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = databaseConnection.getConnection();

		PreparedStatement pStatement;
		ResultSet resultSet;

		resultSet = bukuDao.getDataBuku(getKodeBuku());
		while(resultSet.next()) {
			setJudulBuku(resultSet.getString("judul"));
			setHargaBuku(resultSet.getDouble("harga"));
		}
		
		this.diskonPerBuku = buku.hitungDiskon(getJumlahBuku(), getHargaBuku(), getDiskonPerBuku(), diskon);

		this.hargaTotalperBuku = getHargaBuku() * this.jumlahBuku;

		String sql = "UPDATE detail_nota SET " +
				"id_nota = '"       + getIdNota()     + "'," +
				" judul = '"        + getJudulBuku()  + "'," +
				" jumlah_buku = '"  + getJumlahBuku() + "'," +
				" harga_satuan = '" + getHargaBuku()  + "'," +
				" diskon = '"       + this.diskonPerBuku     + "'," +
				" harga_total = '"  + this.hargaTotalperBuku + "'";

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
