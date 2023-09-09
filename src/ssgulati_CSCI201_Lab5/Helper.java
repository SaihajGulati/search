package ssgulati_CSCI201_Lab5;

import java.util.Arrays;
import java.util.Collections;

public class Helper {

	public static void main (String[] args)
	{
		int[] list = 	new int[100_000_000];
		Arrays.setAll(list, i -> i + 1);
		
		Collections.shuffle(Arrays.asList(list));
		int target1 = (int) (Math.random() * (100_000_000-1));
		int target2 = -1;
		
		SingleThreaded.search(list, target1);
		MultiThreaded.search(list, target1);
		Parallel.search(list, target1);
		
		System.out.println();
		
		SingleThreaded.search(list, target2);
		MultiThreaded.search(list, target2);
		Parallel.search(list, target2);
		
	}

}
