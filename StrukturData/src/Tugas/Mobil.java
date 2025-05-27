package Tugas;

import java.util.ArrayList;
import java.util.Scanner;

public class Mobil {
    String platNomor;

    Mobil(String platNomor) {
        this.platNomor = platNomor;
    }

    @Override
    public String toString() {
        return platNomor;
    }

    static class Parkiran {
        ArrayList<Mobil> daftarMobil = new ArrayList<>();

        void tambahMobil(String platNomor) {
            daftarMobil.add(new Mobil(platNomor));
            System.out.println("Mobil dengan plat " + platNomor + " berhasil ditambahkan.");
        }

        void keluarkanMobil(String platNomor) {
            boolean ditemukan = false;
            for (int i = 0; i < daftarMobil.size(); i++) {
                if (daftarMobil.get(i).platNomor.equalsIgnoreCase(platNomor)) {
                    daftarMobil.remove(i);
                    ditemukan = true;
                    System.out.println("Mobil dengan plat " + platNomor + " berhasil dikeluarkan.");
                    break;
                }
            }
            if (!ditemukan) {
                System.out.println("Mobil dengan plat " + platNomor + " tidak ditemukan.");
            }
        }

        void tampilkanParkiran() {
            if (daftarMobil.isEmpty()) {
                System.out.println("Parkiran kosong.");
            } else {
                System.out.println("Daftar mobil di parkiran:");
                for (Mobil mobil : daftarMobil) {
                    System.out.println("- " + mobil);
                }
            }
        }

        void cariMobil(String platNomor) {
            boolean ada = false;
            for (Mobil mobil : daftarMobil) {
                if (mobil.platNomor.equalsIgnoreCase(platNomor)) {
                    ada = true;
                    break;
                }
            }
            if (ada) {
                System.out.println("Mobil dengan plat " + platNomor + " ada di parkiran.");
            } else {
                System.out.println("Mobil dengan plat " + platNomor + " tidak ditemukan di parkiran.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parkiran parkiran = new Parkiran();

        String[] platAwal = {
            "BA1111AA", "BA2222BB", "BA3333CC",
            "BA4444DD", "BA5555EE", "BA6666FF", "BA7777GG"
        };
        for (String plat : platAwal) {
            parkiran.tambahMobil(plat);
        }

        while (true) {
            System.out.println("\n=== Menu Parkiran Mobil ===");
            System.out.println("1. Tambah mobil ke Parkiran");
            System.out.println("2. Keluarkan mobil dari Parkiran");
            System.out.println("3. Tampilkan isi Parkiran");
            System.out.println("4. Cari mobil di Parkiran");
            System.out.println("5. Keluar");
            System.out.print("Pilih Opsi: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan plat nomor mobil: ");
                    String platMasuk = scanner.nextLine();
                    parkiran.tambahMobil(platMasuk);
                    break;
                case 2:
                    System.out.print("Masukkan plat nomor mobil yang keluar: ");
                    String platKeluar = scanner.nextLine();
                    parkiran.keluarkanMobil(platKeluar);
                    break;
                case 3:
                    parkiran.tampilkanParkiran();
                    break;
                case 4:
                    System.out.print("Masukkan plat nomor yang dicari: ");
                    String platCari = scanner.nextLine();
                    parkiran.cariMobil(platCari);
                    break;
                case 5:
                    System.out.println("Terima kasih. Program selesai.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }
}
