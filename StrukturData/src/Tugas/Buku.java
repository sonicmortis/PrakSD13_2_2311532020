package Tugas;

import java.util.Scanner;
import java.util.Stack;

public class Buku {
    String judul;

    Buku(String judul) {
        this.judul = judul;
    }

    @Override
    public String toString() {
        return judul;
    }

    static class PerpustakaanMini {
        Stack<Buku> tumpukanBuku = new Stack<>();

        void tambahBuku(Buku buku) {
            tumpukanBuku.push(buku);
            System.out.println("Buku \"" + buku.judul + "\" berhasil ditambahkan ke tumpukan.");
        }

        void ambilBuku() {
            if (tumpukanBuku.isEmpty()) {
                System.out.println("Tumpukan buku kosong. Tidak ada buku yang bisa diambil.");
            } else {
                Buku bukuDiambil = tumpukanBuku.pop();
                System.out.println("Buku diambil: " + bukuDiambil.judul);
            }
        }

        void lihatTumpukan() {
            if (tumpukanBuku.isEmpty()) {
                System.out.println("Tumpukan buku kosong.");
            } else {
                System.out.println("Tumpukan Buku Saat Ini:");
                for (int i = tumpukanBuku.size() - 1; i >= 0; i--) {
                    System.out.println("- " + tumpukanBuku.get(i).judul);
                }
            }
        }

        void cariBuku(String judul) {
            boolean ditemukan = false;
            for (Buku buku : tumpukanBuku) {
                if (buku.judul.equalsIgnoreCase(judul)) {
                    ditemukan = true;
                    break;
                }
            }

            if (ditemukan) {
                System.out.println("Buku \"" + judul + "\" masih ada di tumpukan.");
            } else {
                System.out.println("Buku \"" + judul + "\" tidak ditemukan dalam tumpukan.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PerpustakaanMini perpustakaan = new PerpustakaanMini();

        String[] bukuAwal = { "Algoritma Dasar", "Struktur Data", "Basis Data", "Pemrograman Java", "Jaringan Komputer", "Sistem Operasi", "Kecerdasan Buatan" };
        
        for (String judul : bukuAwal) {
            perpustakaan.tambahBuku(new Buku(judul));
        }

        while (true) {
            System.out.println("\n=== MENU PERPUSTAKAAN MINI ===");
            System.out.println("1. Tambah Buku ke Tumpukan");
            System.out.println("2. Ambil Buku Teratas");
            System.out.println("3. Lihat Tumpukan Buku");
            System.out.println("4. Cari Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");

            int pilihan = -1;
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid!");
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan judul buku: ");
                    String judulTambah = scanner.nextLine();
                    perpustakaan.tambahBuku(new Buku(judulTambah));
                    break;
                case 2:
                    perpustakaan.ambilBuku();
                    break;
                case 3:
                    perpustakaan.lihatTumpukan();
                    break;
                case 4:
                    System.out.print("Masukkan judul buku yang dicari: ");
                    String judulCari = scanner.nextLine();
                    perpustakaan.cariBuku(judulCari);
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan Perpustakaan Mini.");
                    return;
                default:
                    System.out.println("Pilihan tidak tersedia, coba lagi.");
            }
        }
    }
}
