package pekan1;

import java.util.ArrayList;

public class ArrayListGetElemen {

	public static void main(String[] args) {
		ArrayList<Integer> list = new
				ArrayList<Integer>();
				list.add(9);
				list.add(5);
				list.add(6);
				System.out.println(list);
				Integer n = list.get(1);
				System.out.println("PAda Indeks ke 1 angkanya adalah:" + n);

	}

}
