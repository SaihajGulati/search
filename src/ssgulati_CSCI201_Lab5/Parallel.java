package ssgulati_CSCI201_Lab5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Parallel {



	public Parallel(int[] list, int target) {
	}
	
	public static void search(int[] list, int target)
	{
		long before = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool();
		int numThreads = 4;
		int start = 0;
		int end = (int) Math.ceil((double)list.length / numThreads);
		boolean found = false;
		
		for (int i = 0; i < numThreads; i++)
		{
			ParallelSearchTask task = new ParallelSearchTask(list, target, start, end, before);
			start = end;
			end += (int) Math.ceil((double)list.length / numThreads);
			if (end > list.length)
			{
				end = list.length;
			}                   
			
			if (pool.invoke(task) != -1)
			{
				found = true;
			}
		}
		
		
		pool.shutdown(); //keeps from threads being thrown away after run
		
		while (!pool.isTerminated())
		{
			Thread.yield();
		}
		
		long after = System.currentTimeMillis();
		long time = after-before;
		
		if (!found)
		{
			System.out.println("PS did not find. Took " + time + " ms");
		}
		
		
	}

}

class ParallelSearchTask extends RecursiveTask<Integer>
{
	private static final long serialVersionUID = 1;
	private int[] list;
	private int start;
	private int end;
	private int target;
	private long startTime;
	
	ParallelSearchTask(int[] list, int target, int start, int end, long startTime)
	{
		this.list = list;
		this.start = start;
		this.end = end;
		this.target = target;
		this.startTime = startTime;
	}
	
	protected Integer compute()
	{
		for (int i = start; i < end; i++) {
            if (list[i] == target) {
            	long time = System.currentTimeMillis() - startTime;
            	System.out.println("PS found at index " + i + " in " + time + " ms");
            	return i;
            }
        }
		return -1;
	}
}
