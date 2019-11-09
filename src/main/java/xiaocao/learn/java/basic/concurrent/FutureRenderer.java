package xiaocao.learn.java.basic.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ImageInfo {
	
	ImageData downloadImage() {
		return null;
	}
	
}

class ImageData {
	
}


public class FutureRenderer {
	
	private final ExecutorService executer = Executors.newSingleThreadExecutor();
	
	void renderPage(CharSequence source) {
		final List<ImageInfo> imageInfos = scanForImageInfo(source);
		Callable<List<ImageData>> task = new Callable<List<ImageData>>() {

			@Override
			public List<ImageData> call() {
				List<ImageData> result = new ArrayList<ImageData>();
				for(ImageInfo imageInfo : imageInfos) {
					result.add(imageInfo.downloadImage());
				}
				return result;
			}
		};
		
		Future<List<ImageData>> future = executer.submit(task);
		// 对文本进行渲染
		//renderText(source);
		try{
			List<ImageData> imageData = future.get();
			for(ImageData data : imageData) {
				// 对图像进行渲染
				//renderImage(data);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			future.cancel(true);
		} catch (ExecutionException e) {
			throw new RuntimeException(e.getCause());
		}
	}

	private List<ImageInfo> scanForImageInfo(CharSequence source) {
		return null;
	}
	
	

}
