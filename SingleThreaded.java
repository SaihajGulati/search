package ssgulati_CSCI201_Lab5;

public class SingleThreaded {
	
	public static void search(int[] list, int target)
	{
		int index = -1;
		long before = System.currentTimeMillis();
		for (int i = 0; i < list.length; ++i)
		{
			if (list[i] == target)
			{
				index = i;
				break;
			}
		}
		
		long after = System.currentTimeMillis();
		
		long time = after-before;
		
		if (index == -1)
		{
			System.out.println("ST Did not find. Took " + time + " ms");
		}
		else
		{
			System.out.println("ST Found at index " + index + " in " + time + " ms");
		}
	}

}
