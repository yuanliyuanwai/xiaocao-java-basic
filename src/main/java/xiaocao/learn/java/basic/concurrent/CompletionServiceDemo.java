package xiaocao.learn.java.basic.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CompletionServiceDemo {
	
	private final ExecutorService executor;
	
	CompletionServiceDemo(ExecutorService executor) {
		this.executor = executor;
	}
	
	void renderPage(CharSequence source) {
		List<ImageInfo> info = scanForImageInfo(source);
		CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);
		for(final ImageInfo imageInfo : info) {
			completionService.submit(new Callable<ImageData>() {
				
				@Override
				public ImageData call() throws Exception {
					return imageInfo.downloadImage();
				}
			});
		}
		// 渲染文本
		//renderText(source);
		try{
			for(int t = 0, n = info.size(); t < n; t++) {
				Future<ImageData> f = completionService.take();
				ImageData imagedata = f.get();
				//renderImage(imagedata);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			throw new RuntimeException(e.getCause());
		}
		
		
	}

	private List<ImageInfo> scanForImageInfo(CharSequence source) {
		return null;
	}

}
