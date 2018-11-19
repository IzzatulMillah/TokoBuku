package tokobuku;

import java.util.Scanner;

public class PresentationLayer {
	
	public Buku insertBuku() {
		String judul;
		long id;
		int saldo;
		double harga;
		String flagAktif;
		
		Scanner scan = new Scanner(System.in);
		
		id = UniqueID.get();
		System.out.println("ID Buku : " + id);
		System.out.print("Judul Buku : ");
		judul = scan.nextLine();
		System.out.print("Saldo : ");
		saldo = scan.nextInt();
		System.out.print("Harga : ");
		harga = scan.nextDouble();
		System.out.print("Flag Aktif : ");
		flagAktif = scan.next();
		
		return new Buku(id, judul, saldo, harga, flagAktif);
		
	}
}
