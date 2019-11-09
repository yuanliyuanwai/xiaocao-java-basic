package xiaocao.learn.java.basic.concurrent;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogServiceDemo {
	
	private final BlockingQueue<String> queue;
	
	private final LoggerThread loggerThread;
	
	private final PrintWriter writer;
	
	private boolean isShutdown;
	
	private int reservations;
	
	public LogServiceDemo(PrintWriter writer) {
		this.queue = new LinkedBlockingQueue<String>();
		this.loggerThread = new LoggerThread();
		this.writer = writer;
	}
	
	public void start() {
		loggerThread.start();
	}
	
	public void stop() {
		synchronized (this) {
			isShutdown = true;
		}
		loggerThread.interrupt();
	}
	
	public void log(String msg) throws InterruptedException {
		synchronized (this) {
			if(isShutdown)
				throw new IllegalStateException();
			++reservations;
		}
		queue.put(msg);
	}
	
	private class LoggerThread extends Thread {
		public void run() {
			try{
				while(true) {
					try{
						synchronized (LogServiceDemo.this) {
							if(isShutdown && reservations == 0) 
								break;
						}
						String msg = queue.take();
						synchronized (LogServiceDemo.this) {
							--reservations;
						}
						writer.println(msg);
					} catch (InterruptedException e) {
						/* retry */
					}
				}
			} finally {
				writer.close();
			}
		}
	}

}
