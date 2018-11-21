package tokobuku;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PresentationLayer {
	Scanner scan = new Scanner(System.in);
	
	public Buku insertBuku() {
		String judul;
		String tipeBuku;
		long id;
		int saldo;
		double harga;
		String flagAktif;
		
		id = UniqueID.get();
		System.out.println("ID Buku : " + id);
		System.out.print("Judul Buku : ");
		judul = scan.nextLine();
		scan.nextLine();
		System.out.print("Tipe Buku : ");
		tipeBuku = scan.nextLine();
		System.out.print("Saldo : ");
		saldo = scan.nextInt();
		System.out.print("Harga : ");
		harga = scan.nextDouble();
		System.out.print("Flag Aktif : ");
		flagAktif = scan.next();
		
		return new Buku(id, judul, tipeBuku, saldo, harga, flagAktif);
		
	}
	
	public NotaPembelian insertHeaderNota() {
		NotaPembelian notaPembelian = new NotaPembelian();
		
		System.out.println("### INPUT HEADER NOTA ###");
		System.out.print("Nama Pembeli   : ");
		notaPembelian.setNamaPembeli(scan.nextLine());
		System.out.print("Alamat Pembeli : ");
		notaPembelian.setAlamatPembeli(scan.nextLine());
		System.out.print("Tanggal Beli   : ");
		notaPembelian.setTanggalNota(scan.nextLine());
		
		return notaPembelian;
	}
	
	public NotaPembelian insertDetailNota() {
		NotaPembelian notaPembelian = new NotaPembelian();
		
		System.out.println("### INPUT DETAIL BUKU ###");
		System.out.println("Kode Nota : ");
		notaPembelian.setIdNota(scan.nextInt());
		System.out.println("Kode Buku : ");
		notaPembelian.setKodeBuku(scan.nextLong());
		System.out.println("Jumlah Buku : ");
		notaPembelian.setJumlahBuku(scan.nextInt());
		
		System.out.println(notaPembelian.toString());
		return notaPembelian;
		
	}
	
	public void showAllBuku() throws SQLException {
		List<Buku> daftarBuku;
		BukuDao bukuDao = new BukuDao();
		daftarBuku = bukuDao.getAllBuku();
		
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
	
	public void showAllNotaPembelian() {
		
	}
}
