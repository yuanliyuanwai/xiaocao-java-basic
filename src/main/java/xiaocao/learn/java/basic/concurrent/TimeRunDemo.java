package xiaocao.learn.java.basic.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeRunDemo {
	
	private static ScheduledExecutorService cancelExec = Executors.newSingleThreadScheduledExecutor();
	
	public static void timeRun1(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
		class RethrowableTask implements Runnable {
			private volatile Throwable t;
			public void run() {
				try{
					r.run();
				} catch (Throwable t) {
					this.t = t;
				}
			}
			
			void rethrow() {
				if(t != null)
					launderThrowable(t);
			}
		}
		RethrowableTask task = new RethrowableTask();
		final Thread taskThread = new Thread(task);
		taskThread.start();
		cancelExec.schedule(new Runnable() {
			
			@Override
			public void run() {
				taskThread.interrupt();
			}
		}, timeout, unit);
		taskThread.join(unit.toMillis(timeout));
		task.rethrow();
	}
	
	public static RuntimeException launderThrowable(Throwable t){
	    if (t instanceof RuntimeException) return (RuntimeException) t;
	 
	    else if  ( t instanceof Error) throw (Error)t;
	 
	    else throw new IllegalStateException("Not unchecked", t);
	}
	
	
	public static void timeRun2(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
		ExecutorService taskExec = Executors.newCachedThreadPool();
		Future<?> task = taskExec.submit(r);
		try{
			task.get(timeout, unit);
		} catch (TimeoutException e) {
			// 接下来任务被取消
		} catch (ExecutionException e) {
			// 如果在任务中抛出了异常，那么重新抛出该异常
			throw launderThrowable(e.getCause());
		} finally {
			// 如果任务已经结束，那么执行取消操作不会带来任何影响
			task.cancel(true); //如果任务正在运行则会中断
			taskExec.shutdown();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("user start...");
					Thread.sleep(3000);
					System.out.println("user end...");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		//timeRun1(r, 1, TimeUnit.SECONDS);
		timeRun2(r, 3, TimeUnit.SECONDS);
		cancelExec.shutdown();
	}


}
