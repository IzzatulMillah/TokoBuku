package tokobuku;

import java.sql.ResultSet;
import java.sql.SQLException;

import payment.CashInjector;
import payment.CreditInjector;
import payment.PaymentApplication;

public class BusinessLogicPembelian {
	PresentationLayer presentationLayer = new PresentationLayer();
	Pembeli pembeli;
	NotaPembelian notaPembelian;

	public BusinessLogicPembelian() {

	}

	public void insertHeader() throws SQLException, CustomException {
		notaPembelian = presentationLayer.insertHeaderNota();
		String kota = notaPembelian.getKotaPembeli();
		
		validasiKotaHarusAda(kota);
		notaPembelian.insertHeader();
		insertDetail();
		
		Main.counter++;
	}

	public void insertDetail() throws SQLException, CustomException {
		notaPembelian = presentationLayer.insertDetailNota();
		
		validasiKodeNotaHarusAda();
		validasiBukuHarusAda(notaPembelian.getKodeBuku());
		notaPembelian.insertDetail();
	}
	
	public void insertPembayaran(String msg, double nilai) {
        
		CashInjector cashInjector = new CashInjector();
        PaymentApplication app = cashInjector.getPayment();
        app.paymentProcess(msg, nilai);

        CreditInjector creditInjector = new CreditInjector();
        PaymentApplication apps = creditInjector.getPayment();
        apps.paymentProcess(msg, nilai);
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