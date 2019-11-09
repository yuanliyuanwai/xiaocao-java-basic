package xiaocao.learn.java.basic.concurrent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 获取已经取消的任务的示例
 * 任务执行框架的shutdownNow()方法会尝试取消正在执行的任务
 * 并返回所有已提交但是还没开始的任务
 * 这里通过特殊方案来获取已经取消的任务
 * @author Administrator
 *
 */

class TrackingExecutor extends AbstractExecutorService {
	
	private final ExecutorService exec;
	
	private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());
	
	public TrackingExecutor(ExecutorService exec) {
		this.exec = exec;
	}

	@Override
	public void shutdown() {
		exec.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return exec.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		return exec.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return exec.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return exec.awaitTermination(timeout, unit);
	}

	@Override
	public void execute(final Runnable command) {
		exec.execute(new Runnable() {
			
			@Override
			public void run() {
				try{
					command.run();
				} finally{
					if(isShutdown() && Thread.currentThread().isInterrupted()) {
						tasksCancelledAtShutdown.add(command);
					}
				}
				
			}
		});
		
	}
	
	public List<Runnable> getCancelledTasks() {
		if(!exec.isTerminated())
			throw new IllegalStateException();
		return new ArrayList<Runnable>(tasksCancelledAtShutdown);
	}
	
}

/**
 * 网络爬虫
 * @author Administrator
 *
 */
abstract class WebCrawler {
	
	private volatile TrackingExecutor exec;
	
	private final Set<URL> urlsToCrawl = new HashSet<URL>();
	
	public synchronized void start() {
		exec = new TrackingExecutor(Executors.newCachedThreadPool());
		for(URL url : urlsToCrawl) submitCrawlTask(url);
		urlsToCrawl.clear();
	}
	
	public synchronized void stop() throws InterruptedException {
		try{
			saveUncrawled(exec.shutdownNow());
			if(exec.awaitTermination(3, TimeUnit.SECONDS)) {
				saveUncrawled(exec.getCancelledTasks());
			}
		} finally {
			exec = null;
		}
	}
	
	protected abstract List<URL> processPage(URL url);
	
	private void saveUncrawled(List<Runnable> uncrawled) {
		for(Runnable task : uncrawled) {
			urlsToCrawl.add(((CrawlTask)task).getpage());
		}
	}
	
	private void submitCrawlTask(URL u) {
		exec.execute(new CrawlTask(u));
	}
	
	
	private class CrawlTask implements Runnable {
		private final URL url;
		
		public CrawlTask(URL url) {
			this.url = url;
		}
		
		public void run() {
			for(URL link : processPage(url)) {
				if(Thread.currentThread().isInterrupted()) return;
				submitCrawlTask(link);
			}
		}
		public URL getpage() {
			return url;
		}
	}
}


public class GetCancelTaskDemo {

}
