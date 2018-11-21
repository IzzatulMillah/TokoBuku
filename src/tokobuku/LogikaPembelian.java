package tokobuku;

import java.sql.SQLException;

public class LogikaPembelian {
	PresentationLayer presentationLayer = new PresentationLayer();
	Pembeli pembeli;
	NotaPembelian notaPembelian;

	public LogikaPembelian() {
		
	}
	
	public void insertHeader() throws SQLException {
		
		presentationLayer.insertHeaderNota();
	}
}