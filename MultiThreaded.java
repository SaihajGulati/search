package ssgulati_CSCI201_Lab5;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreaded {

	
	public static void search(int[] list, int target)
	{
		long before = System.currentTimeMillis();
		int numThreads = 4;
		ExecutorService executor = Executors.newCachedThreadPool();
		int start = 0;
		int end = (int) Math.ceil((double)list.length / numThreads);
		boolean found[] = new boolean[1];
		
		for (int i = 0; i < numThreads; i++)
		{
			SearchTask task = new SearchTask(list, target, start, end, before, found);
			start = end;
			end += (int) Math.ceil((double)list.length / numThreads);
			if (end > list.length)
			{
				end = list.length;
			}                   
			executor.execute(task);
			
		}
		
		executor.shutdown(); //keeps from threads being thrown away after run
		while (!executor.isTerminated())
		{
			Thread.yield();
		}
		
		long after = System.currentTimeMillis();
		long time = after-before;
		
		
		
		if (!found[0])
		{
			System.out.println("MT did not find. Took " + time + " ms");
		}
		
		
		
		
	}

}

class SearchTask extends Thread
{
	private int[] list;
	private int start;
	private int end;
	private int target;
	private long startTime;
	private boolean[] found;
	
	SearchTask(int[] list, int target, int start, int end, long startTime, boolean[] found)
	{
		this.list = list;
		this.start = start;
		this.end = end;
		this.target = target;
		this.startTime = startTime;
		this.found = found;
	}
	
	public void run()
	{
		for (int i = start; i < end; i++) {
            if (list[i] == target) {
            	long time = System.currentTimeMillis() - startTime;
            	found[0] = true;
            	System.out.println("MT found at index " + i + " in " + time + " ms");
            }
        }
	}
}
