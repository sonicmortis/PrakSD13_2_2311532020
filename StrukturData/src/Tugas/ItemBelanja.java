package Tugas;

import java.util.Scanner;

public class ItemBelanja {
    public static void main(String[] args) {
        DaftarBelanja daftar = new DaftarBelanja();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("=== MENU DAFTAR BELANJA ===");
            System.out.println("1. Tambah Item");
            System.out.println("2. Hapus Item");
            System.out.println("3. Tampilkan Semua Item");
            System.out.println("4. Tampilkan Item Berdasarkan Kategori");
            System.out.println("5. Cari Item");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama item: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan kuantitas: ");
                    int kuantitas = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Masukkan kategori: ");
                    String kategori = scanner.nextLine();
                    daftar.tambahItem(nama, kuantitas, kategori);
                    break;
                case 2:
                    System.out.print("Masukkan nama item yang ingin dihapus: ");
                    String namaHapus = scanner.nextLine();
                    daftar.hapusItem(namaHapus);
                    break;
                case 3:
                    daftar.tampilkanSemuaItem();
                    break;
                case 4:
                    System.out.print("Masukkan kategori: ");
                    String kategoriCari = scanner.nextLine();
                    daftar.tampilkanPerKategori(kategoriCari);
                    break;
                case 5:
                    System.out.print("Masukkan nama item yang ingin dicari: ");
                    String namaCari = scanner.nextLine();
                    daftar.cariItem(namaCari);
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
            System.out.println();
        } while (pilihan != 6);

        scanner.close();
    }
}

class Node {
    String nama;
    int kuantitas;
    String kategori;
    Node prev;
    Node next;

    Node(String nama, int kuantitas, String kategori) {
        this.nama = nama;
        this.kuantitas = kuantitas;
        this.kategori = kategori;
    }
}

class DaftarBelanja {
    Node head;
    Node tail;

    void tambahItem(String nama, int kuantitas, String kategori) {
        Node baru = new Node(nama, kuantitas, kategori);
        if (head == null) {
            head = tail = baru;
        } else {
            tail.next = baru;
            baru.prev = tail;
            tail = baru;
        }
        System.out.println("Item berhasil ditambahkan!");
    }

    void hapusItem(String nama) {
        if (head == null) {
            System.out.println("Daftar belanja kosong.");
            return;
        }

        Node current = head;
        while (current != null) {
            if (current.nama.equalsIgnoreCase(nama)) {
                if (current == head && current == tail) {
                    head = tail = null;
                } else if (current == head) {
                    head = head.next;
                    head.prev = null;
                } else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Item berhasil dihapus.");
                return;
            }
            current = current.next;
        }
        System.out.println("Item tidak ditemukan.");
    }

    void tampilkanSemuaItem() {
        if (head == null) {
            System.out.println("Daftar belanja kosong.");
            return;
        }

        System.out.println("--- Daftar Belanja ---");
        Node current = head;
        while (current != null) {
            System.out.println("- " + current.nama + " (" + current.kuantitas + ") [" + current.kategori + "]");
            current = current.next;
        }
    }

    void tampilkanPerKategori(String kategori) {
        boolean ditemukan = false;
        Node current = head;
        System.out.println("--- Item dalam kategori '" + kategori + "' ---");
        while (current != null) {
            if (current.kategori.equalsIgnoreCase(kategori)) {
                System.out.println("- " + current.nama + " (" + current.kuantitas + ")");
                ditemukan = true;
            }
            current = current.next;
        }
        if (!ditemukan) {
            System.out.println("Tidak ada item dalam kategori ini.");
        }
    }

    void cariItem(String nama) {
        Node current = head;
        while (current != null) {
            if (current.nama.equalsIgnoreCase(nama)) {
                System.out.println("Item ditemukan: " + current.nama + " (" + current.kuantitas + ") [" + current.kategori + "]");
                return;
            }
            current = current.next;
        }
        System.out.println("Item tidak ditemukan.");
    }
}
