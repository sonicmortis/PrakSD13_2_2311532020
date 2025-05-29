package Tugas;

import java.util.*;

public class AntrianRestoran {
    static class Pelanggan {
        String id;
        int jumlahPesanan;

        Pelanggan(String id, int jumlahPesanan) {
            this.id = id;
            this.jumlahPesanan = jumlahPesanan;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Pelanggan> antrian = new LinkedList<>();

        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split(" ");
            String id = data[0];
            int jumlahPesanan = Integer.parseInt(data[1]);
            antrian.add(new Pelanggan(id, jumlahPesanan));
        }

        int waktuKumulatif = 0;

        while (!antrian.isEmpty()) {
            Pelanggan sekarang = antrian.poll();
            waktuKumulatif += sekarang.jumlahPesanan;
            System.out.println(sekarang.id + " selesai dalam " + waktuKumulatif + " menit");
        }

        scanner.close();
    }
}

