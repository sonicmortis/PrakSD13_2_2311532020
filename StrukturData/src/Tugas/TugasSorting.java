package Tugas;

public class TugasSorting {
    public static void main(String[] args) {
        char[] array = {'v','w','x','y','z','u','t','s','r','q','p','o','n','m','l','k','j','i','h','g','f','e','d','c','b','a'};

        int start = array.length - 5;  
        int end = array.length;        

        for (int i = start + 1; i < end; i++) {
            char key = array[i];
            int j = i - 1;
            while (j >= start && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }

        for (char c : array) {
            System.out.print(c + "-");
        }
    }
}
