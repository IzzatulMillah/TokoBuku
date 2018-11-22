package tokobuku;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

public class LogikaPembelian {
	PresentationLayer presentationLayer = new PresentationLayer();
	Pembeli pembeli;
	NotaPembelian notaPembelian;

	public LogikaPembelian() {

	}

	public void insertHeader() throws SQLException, CustomException {
		notaPembelian = presentationLayer.insertHeaderNota();
		String kota = notaPembelian.getKotaPembeli();
		validasiKotaHarusAda(kota);
		notaPembelian.insertHeader();
	}

	public void insertDetail() throws SQLException, CustomException {
		notaPembelian = presentationLayer.insertDetailNota();
		validasiKodeNotaHarusAda();
		validasiBukuHarusAda(notaPembelian.getKodeBuku());
		notaPembelian.insertDetail();
	}

	public void validasiKotaHarusAda(String kota) throws CustomException, IllegalArgumentException {
		Kota[] daftarKota = {Kota.BALI, Kota.BALIKPAPAN, Kota.BANDUNG, 
				Kota.BANYUWANGI, Kota.BATAM, Kota.BEKASI, 
				Kota.BONDOWOSO, Kota.DEPOK, Kota.GRESIK, 
				Kota.JAKARTA, Kota.JAYAPURA, Kota.KEDIRI,
				Kota.LAMPUNG, Kota.PADANG, Kota.PASURUAN, 
				Kota.PURWOKERTO, Kota.SAMARINDA, Kota.SEMARANG, 
				Kota.SIDOARJO, Kota.SURABAYA, Kota.YOGYAKARTA};
		EnumSet<Kota> listKotaAda;
		String namaKota = kota;

		listKotaAda = EnumSet.of(Kota.SURABAYA, daftarKota); 

		if (!listKotaAda.contains(Kota.valueOf(namaKota))) {
			throw new CustomException("Nama kota belum ada di master");
		}

	}

	public void validasiKodeNotaHarusAda() throws SQLException, CustomException {
		NotaDAO notaDAO = new NotaDAO();
		boolean isExist;
		
		isExist = notaDAO.isKodeNotaExist();
		if(!isExist) {
			throw new CustomException("Kode Bagian tidak ada di database.");
		}
	}

	public void validasiBukuHarusAda(long kodeBuku) throws SQLException, CustomException {
		BukuDao bukuDao = new BukuDao();
		ResultSet resultSet;
		
		resultSet = bukuDao.getDataBuku(kodeBuku);
		
		while(!resultSet.next()) {
			throw new CustomException("Kode Buku tidak ada di database");
		}
	}

	public void validasiSaldoTidakKosong() {}

	public void validasiBukuHarusAktif() {}
}