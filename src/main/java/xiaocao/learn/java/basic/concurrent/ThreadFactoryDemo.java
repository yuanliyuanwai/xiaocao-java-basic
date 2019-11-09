package xiaocao.learn.java.basic.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * 自定义线程工厂的demo
 * @author Administrator
 *
 */

public class ThreadFactoryDemo implements ThreadFactory {
	
	private final String poolName;
	
	public ThreadFactoryDemo(String poolName) {
		this.poolName = poolName;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new MyAppThread(r, poolName);
	}

}

class MyAppThread extends Thread {
	
	public static final String DEFAULT_NAME = "MyAppThread";
	private static final AtomicInteger  created = new AtomicInteger();
	private static final AtomicInteger alive = new AtomicInteger();
	
	public MyAppThread(Runnable r) {
		this(r, DEFAULT_NAME);
	}
	
	public MyAppThread(Runnable r, String name) {
		super(r, name + "-" + created.incrementAndGet());
		setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.err.print("uncaught in thread " + t.getName() + ":" + e.getMessage());
				
			}
		});
	}
	
	@Override
	public void run() {
		try{
			alive.incrementAndGet();
			super.run();
		} finally {
			alive.decrementAndGet();
		}
	}
	
	public static int getThreadCreated() {
		return created.get();
	}
	
	public static int getThreadAlive() {
		return alive.get();
	}
	
}
