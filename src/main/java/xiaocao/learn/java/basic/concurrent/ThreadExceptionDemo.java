package xiaocao.learn.java.basic.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;



class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.err.print(t.getName() + ":" + e);
	}
	
}


public class ThreadExceptionDemo extends Thread {

	public static final String DEFAULT_NAME = "MyAppThread";

	private static volatile boolean debugLifecycle = false;

	private static final AtomicInteger created = new AtomicInteger();

	private static final AtomicInteger alive = new AtomicInteger();

	public ThreadExceptionDemo(Runnable r) {
		this(r, DEFAULT_NAME);
	}

	public ThreadExceptionDemo(Runnable r, String defaultName) {
		super(r, defaultName + "-" + created.incrementAndGet());
		this.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("UNCAUGHT in thread " + t.getName());
			}
		});
	}

	public void run() {
		boolean debug = debugLifecycle;
		if (debug)
			System.err.print("Created " + getName());
		try {
			alive.incrementAndGet();
			super.run();
		} finally {
			alive.decrementAndGet();
			if (debug)
				System.err.print("Exiting " + getName());
		}
	}

	public static int getThreadsCreated() {
		return created.get();
	}

	public static int getThreadAlive() {
		return alive.get();
	}

	public static boolean getDebug() {
		return debugLifecycle;
	}

	public static void setDebug(boolean b) {
		debugLifecycle = b;
	}

	public static void main(String[] args) {
		
		final ThreadExceptionHandler eh = new ThreadExceptionHandler();
		
		ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				// TODO Auto-generated method stub
				Thread t = new Thread(r);
				t.setUncaughtExceptionHandler(eh);
				return t;
			}
		});
		
		exec.execute(new Runnable() {
			@Override
			public void run() {
				int a = 3;
				int b = 0;
				int c = a / b;
				System.out.println(c);
			}
		});
		exec.shutdown();

	}

}
