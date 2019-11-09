package xiaocao.learn.java.basic.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
	
	private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {

		@Override
		public ProductInfo call() throws Exception {
			return new ProductInfo();
		}
	});
	
	
	private final Thread thread = new Thread(future);
	
	public void start() {
		this.thread.start();
	}
	
	public ProductInfo get() throws InterruptedException{
		try {
			return future.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			throw launderThrowable(cause);
		}
	}
	
	private class ProductInfo {
		
	}
	
	public static RuntimeException launderThrowable(Throwable t) {
		if(t instanceof RuntimeException) return (RuntimeException) t;
		else if(t instanceof Error) throw (Error) t;
		else  throw new IllegalStateException("Not unchecked", t);
	}

}
