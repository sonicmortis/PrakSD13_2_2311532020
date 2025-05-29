package Tugas;

import java.util.Scanner;

public class AntrianPasien {
    static class Node {
        int noAntrian;
        String nama;
        String keluhan;
        Node next;

        Node(int noAntrian, String nama, String keluhan) {
            this.noAntrian = noAntrian;
            this.nama = nama;
            this.keluhan = keluhan;
            this.next = null;
        }
    }

    private Node head = null;
    private Node tail = null;

    public void tambahPasien(int noAntrian, String nama, String keluhan) {
        Node baru = new Node(noAntrian, nama, keluhan);
        if (isEmpty()) {
            head = tail = baru;
        } else {
            tail.next = baru;
            tail = baru;
        }
        System.out.println("Data pasien berhasil ditambahkan!");
    }

    public void tampilkanAntrian() {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }
        Node temp = head;
        int urutan = 1;
        System.out.println("--- Daftar Antrian Pasien ---");
        while (temp != null) {
            System.out.println(urutan + ". [" + temp.noAntrian + "] " + temp.nama + " - " + temp.keluhan);
            temp = temp.next;
            urutan++;
        }
    }

    public void hapusPasienPertama() {
        if (isEmpty()) {
            System.out.println("Tidak ada pasien dalam antrian.");
            return;
        }
        System.out.println("Pasien dengan nama " + head.nama + " telah dilayani (dihapus dari antrian).");
        head = head.next;
        if (head == null) tail = null; 
    }

    public void cariPasien(String nama) {
        if (isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }
        Node temp = head;
        boolean ditemukan = false;
        while (temp != null) {
            if (temp.nama.equalsIgnoreCase(nama)) {
                System.out.println("Ditemukan: [" + temp.noAntrian + "] " + temp.nama + " - " + temp.keluhan);
                ditemukan = true;
                break;
            }
            temp = temp.next;
        }
        if (!ditemukan) {
            System.out.println("Pasien dengan nama " + nama + " tidak ditemukan.");
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int hitungPasien() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AntrianPasien klinik = new AntrianPasien();
        int pilihan;

        do {
            System.out.println("\n=== SISTEM ANTRIAN PASIEN KLINIK ===");
            System.out.println("1. Tambah Pasien");
            System.out.println("2. Tampilkan Antrian");
            System.out.println("3. Layani Pasien (Hapus Antrian Pertama)");
            System.out.println("4. Cari Pasien");
            System.out.println("5. Jumlah Pasien");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Buang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nomor Antrian: ");
                    int no = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan Nama Pasien: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Keluhan: ");
                    String keluhan = scanner.nextLine();
                    klinik.tambahPasien(no, nama, keluhan);
                    break;
                case 2:
                    klinik.tampilkanAntrian();
                    break;
                case 3:
                    klinik.hapusPasienPertama();
                    break;
                case 4:
                    System.out.print("Masukkan Nama Pasien yang dicari: ");
                    String cari = scanner.nextLine();
                    klinik.cariPasien(cari);
                    break;
                case 5:
                    System.out.println("Jumlah pasien saat ini: " + klinik.hitungPasien());
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan sistem antrian.");
                    break;
                default:
                    System.out.println("Menu tidak valid.");
            }
        } while (pilihan != 6);

        scanner.close();
    }
}

