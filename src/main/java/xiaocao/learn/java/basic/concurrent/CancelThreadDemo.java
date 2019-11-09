package xiaocao.learn.java.basic.concurrent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

interface CancellableTask<T> extends Callable<T> {
	void cancel();
	
	RunnableFuture<T> newTask();
}

class CancellingExecutor extends ThreadPoolExecutor  {

	public CancellingExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if(callable instanceof CancellableTask) {
			return ((CancellableTask<T>)callable).newTask();
		} else {
			return super.newTaskFor(callable);
		}
	}
}

abstract class SocketUsingTask<T> implements CancellableTask<T> {
	private Socket socket;
	protected synchronized void setSocket(Socket s) {this.socket = s;}
	
	public synchronized void cancel() {
		try{
			if(socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			
		}
	}
	
	public RunnableFuture<T> newTask() {
		return new FutureTask<T>(this) {
			@Override
			// 这样既能在线程调用可以中断的方法时可以取消而且在调用可阻塞的
			// 套接字IO方法时也可以取消
			public boolean cancel(boolean mayInterruptIfRunning) {
				try{
					SocketUsingTask.this.cancel();
				} finally {
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}
}


public class CancelThreadDemo {
	
}
