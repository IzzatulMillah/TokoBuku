package tokobuku;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static int counter;
	
	static {
		counter = 0;
		if(counter > 10) {
			System.out.println("Promo telah habis. Coba lagi season selanjutnya");
		}
	}

	public static void main(String[] args) throws SQLException, CustomException, InputMismatchException {
		BusinessLogicPembelian blPembelian = new BusinessLogicPembelian();
		try {
			blPembelian.insertHeader();
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void showMenu() throws CustomException, InputMismatchException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("==== TOKO BUKU ====");
		System.out.println("1. Lihat Daftar Buku");
		System.out.println("2. Tambah Daftar Buku");
		System.out.println("3. Input Pembelian Buku");
		System.out.print("==> Masukkan pilihan : ");
		int pilihan = scanner.nextInt();

		switch(pilihan) {
		case 1 :
			showBuku();
			break;
		case 2 :
			insertBuku();
			break;
		case 3 :
			insertPembelian();
			break;
		}
	}

	public static void showBuku() throws CustomException {
		PresentationLayer presentationLayer = new PresentationLayer();
		try {
			presentationLayer.showAllBuku();
		} catch (CustomException e) {
			System.out.println(e.getStatus());
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public static void insertBuku() {
		PresentationLayer presentationLayer = new PresentationLayer();
		Buku buku = new Buku();

		buku = presentationLayer.insertBuku();

		try {
			buku.insert();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertPembelian() {
		BusinessLogicPembelian blPembelian = new BusinessLogicPembelian();
		try {
			blPembelian.insertHeader();
		} catch (SQLException | CustomException e) {
			System.out.println(e);
		}
	}
}
