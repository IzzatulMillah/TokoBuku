package tokobuku;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PresentationLayer {
	Scanner scan = new Scanner(System.in);
	
	public Buku insertBuku() {
		
		long id = UniqueID.get();
		System.out.println("ID Buku : " + id);
		System.out.print("Judul Buku : ");
		String judul = scan.nextLine();
		System.out.print("Tipe Buku : ");
		String tipeBuku = scan.nextLine();
		System.out.print("Saldo : ");
		int saldo = scan.nextInt();
		System.out.print("Harga : ");
		double harga = scan.nextDouble();
		
		return new Buku(id, judul, tipeBuku, saldo, harga, null);
	}
	
	public NotaPembelian insertHeaderNota() {
		NotaPembelian notaPembelian = new NotaPembelian();
		
		System.out.println("### INPUT HEADER NOTA ###");
		System.out.print("Nama Kasir     : ");
		notaPembelian.setNamaKasir(scan.nextLine());
		System.out.print("Nama Pembeli   : ");
		notaPembelian.setNamaPembeli(scan.nextLine());
		System.out.print("Alamat Pembeli : ");
		notaPembelian.setAlamatPembeli(scan.nextLine());
		System.out.print("Kota Pembeli   : ");
		notaPembelian.setKotaPembeli(scan.nextLine());
		System.out.print("Tanggal Beli   : ");
		notaPembelian.setTanggalNota(scan.nextLine());
		System.out.print("Diskon Member  : ");
		notaPembelian.setDiskonPerMember(scan.nextDouble());
		
		return notaPembelian;
	}
	
	public NotaPembelian insertDetailNota() {
		NotaPembelian notaPembelian = new NotaPembelian();
		
		System.out.println("### INPUT DETAIL BUKU ###");
		System.out.print("Kode Nota : ");
		notaPembelian.setIdNota(scan.nextInt());
		System.out.print("Kode Buku : ");
		notaPembelian.setKodeBuku(scan.nextLong());
		System.out.print("Jumlah Buku : ");
		notaPembelian.setJumlahBuku(scan.nextInt());
		
		return notaPembelian;
	}
	
	public NotaPembelian insertPayment() {
		NotaPembelian notaPembelian = new NotaPembelian();
		
		System.out.println("### INPUT JENIS PAYMENT ###");
		System.out.println("Jenis Payment          : ");
		notaPembelian.setJenisPembayaran(scan.next());
		System.out.println("Jumlah yang dibayarkan : ");
		notaPembelian.setJumlahPembayaran(scan.nextDouble());
		
		return notaPembelian;
	}
	
	public void showAllBuku() throws SQLException, CustomException {
		List<Buku> daftarBuku;
		BukuDao bukuDao = new BukuDao();
		daftarBuku = bukuDao.getAllBuku();
		
		System.out.println("\n*********** DAFTAR BUKU ***********\n");
		
		for(Buku buku : daftarBuku) {
			System.out.println("ID buku    : " + buku.getIdBuku());
			System.out.println("Judul      : " + buku.getJudul());
			System.out.println("Tipe Buku  : " + buku.getTipeBuku());
			System.out.println("Saldo      : " + buku.getSaldo());
			System.out.println("Harga      : " + buku.getHarga());
			System.out.println("Flag Aktif : " + buku.getFlagAktif());
			System.out.println("--------------------------------------------");
		}
	}
	
	public void showAllNota() {
		System.out.println("\n******** TAMPILAN NOTA ********");
		System.out.println("Masukkan tanggal nota (YYYY/MM/DD) : ");
		String tanggal = scan.next();
	}
}
