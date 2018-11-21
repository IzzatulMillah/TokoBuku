package tokobuku;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		Buku buku;
		
//		PresentationLayer presentationLayer = new PresentationLayer();
//		presentationLayer.insertHeaderNota();
		
		LogikaPembelian logikaPembelian = new LogikaPembelian();
		logikaPembelian.insertHeader();
	}
}
