package tokobuku;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Buku buku;
		
		PresentationLayer presentationLayer = new PresentationLayer();
		buku = presentationLayer.insertBuku();
		buku.insert();
	}
}
