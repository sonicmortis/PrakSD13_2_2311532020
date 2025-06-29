package Tugas;

import java.util.*;

public class TugasSortingLanjutan {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Nama: Muhammad Luthfi Kautsar Rizata
        // NIM: 2311532020

        System.out.print("Banyak bilangan prima (1-50): ");
        int n = input.nextInt();

        while (n < 1 || n > 50) {
            System.out.println("Jumlah harus antara 1 sampai 50!");
            System.out.print("Masukkan ulang: ");
            n = input.nextInt();
        }

        int[] data = new int[n];
        System.out.println("Masukkan " + n + " bilangan prima:");

        int count = 0;
        while (count < n) {
            int inputNum = input.nextInt();
            if (isPrime(inputNum)) {
                data[count] = inputNum;
                count++;
            } else {
                System.out.println("Bukan bilangan prima!");
                System.out.println("Silakan masukkan lagi: ");
            }
        }

        System.out.println("Deret awal: " + Arrays.toString(data));
        System.out.println("Algoritma: Merge Sort");
        System.out.println("Proses sorting:");

        mergeSort(data, 0, data.length - 1);

        System.out.println("Setelah sorting: " + Arrays.toString(data));
    }

    static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
            System.out.println(Arrays.toString(Arrays.copyOfRange(array, left, right + 1)));
        }
    }

    static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = array[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k++] = L[i++];
            } else {
                array[k++] = R[j++];
            }
        }

        while (i < n1)
            array[k++] = L[i++];
        while (j < n2)
            array[k++] = R[j++];
    }
}

