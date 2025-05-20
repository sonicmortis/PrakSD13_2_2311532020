package Pekan4;

import java.util.LinkedList;
import java.util.*;

public class ContohQueue2 {

	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 0; i < 6; i++) 
			q.add(i);
		
		System.out.println("Elemen Antrian " + q);
		
		int hapus = q.remove();
		System.out.println("Hapus elemen = " + hapus);
		System.out.println(q);
		
		int depan = q.peek();
		System.out.println("Kepala Antrian = " + depan);
		
		int banyak = q.size();
		System.out.println("Size Antrian = " + banyak);

	}

}
