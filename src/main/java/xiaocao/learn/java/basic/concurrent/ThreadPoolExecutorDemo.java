package xiaocao.learn.java.basic.concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPoolExecutorDemo extends ThreadPoolExecutor {
	
	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	
	private final AtomicLong numTasks = new AtomicLong();
	
	private final AtomicLong totalTime = new AtomicLong();
	
	public ThreadPoolExecutorDemo(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		// TODO Auto-generated method stub
		super.beforeExecute(t, r);
		startTime.set(System.nanoTime());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		// TODO Auto-generated method stub
		try{
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			System.out.println("taskTime:" + taskTime);
		} finally {
			super.afterExecute(r, t);
		}
		
	}

	@Override
	protected void terminated() {
		try{
			System.out.println("Terminated: avg time =" + totalTime.get() / numTasks.get());
		} finally {
			super.terminated();
		}
		
	}
	
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Random random = new Random(47);
				try {
					Thread.sleep(random.nextInt(1000) + 4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		ThreadPoolExecutorDemo exec = new ThreadPoolExecutorDemo(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
		exec.submit(t);
		exec.submit(t);
		exec.shutdown();
	}
	
}
