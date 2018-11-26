package tokobuku;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import payment.CashInjector;
import payment.CreditInjector;
import payment.PaymentApplication;
import tokobuku.Buku.DiskonTambahan;

public class BusinessLogicPembelian {
	PresentationLayer presentationLayer = new PresentationLayer();
	Pembeli pembeli;
	NotaPembelian notaPembelian;

	public BusinessLogicPembelian() {

	}

	public void insertHeader() throws SQLException, CustomException {
		notaPembelian = presentationLayer.insertHeaderNota();
		
		validasiKotaHarusAda(notaPembelian.getKotaPembeli());
		notaPembelian.insertHeader();
		insertDetail();
		
		Main.counter++;
	}

	public void insertDetail() throws SQLException, CustomException {
		notaPembelian = presentationLayer.insertDetailNota();
		
		validasiKodeNotaHarusAda();
		validasiBukuHarusAda(notaPembelian.getKodeBuku());
		diskonTambahan.getProsenDiskonTambahan(notaPembelian.getJudulBuku(), notaPembelian.getJumlahBuku());
		notaPembelian.insertDetail();
		insertPembayaran(notaPembelian.getJenisPembayaran(), notaPembelian.getJumlahPembayaran());
	}
	
	public void insertPembayaran(String msg, double nilai) {
		if(msg.equalsIgnoreCase("CASH")) {
			CashInjector cashInjector = new CashInjector();
	        PaymentApplication app = cashInjector.getPayment();
	        app.paymentProcess(msg, nilai);
		} else if(msg.equalsIgnoreCase("CREDIT")) {
			CreditInjector creditInjector = new CreditInjector();
	        PaymentApplication apps = creditInjector.getPayment();
	        apps.paymentProcess(msg, nilai);
		}
	}
	
	DiskonTambahan diskonTambahan = new DiskonTambahan() {
		public double getProsenDiskonTambahan(String jenisBuku, int jumlah) {
			if(jenisBuku.equalsIgnoreCase("edukasi")) {
				if(jumlah > 10) {
					return 10;
				}
			}
			return 0;
		}

		@Override
		public double getProsenDiskonTambahan() {
			return 0;
		}
	};
	
	public <E> void hitungProporsionalDiskonHeader(double diskonHeader, List<E> detail) {
		double totalDetail = 0.0;
		// TODO edit dependency di sini
		for(E buku : detail) {
			double nilaiDetilSetelahDiskonDetil;
		}
	}

	public void validasiKotaHarusAda(String inputanKota) throws CustomException, IllegalArgumentException {
		for(Kota kota : Kota.values()) {
			if(!kota.name().equalsIgnoreCase(inputanKota)) {
				throw new CustomException("Nama kota belum ada di master");
			}
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
}