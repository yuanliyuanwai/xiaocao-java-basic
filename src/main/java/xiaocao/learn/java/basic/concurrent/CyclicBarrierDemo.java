package xiaocao.learn.java.basic.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @ClassName: CyclicBarrierDemo
 * @Description:
 * @author zhengchong.wan
 * @date 2019年11月9日 下午7:21:02
 *
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
			@Override
			public void run() {
				System.out.println("集合完成!");

			}
		});
		for (int i = 0; i < 3; i++) {
			Runnable runnale = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点1，当前已有"
								+ (barrier.getNumberWaiting() + 1) + "个已到达"
								+ (barrier.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
						try {
							barrier.await();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点2，当前已有"
								+ (barrier.getNumberWaiting() + 1) + "个已到达"
								+ (barrier.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
						try {
							barrier.await();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点3，当前已有"
								+ (barrier.getNumberWaiting() + 1) + "个已到达"
								+ (barrier.getNumberWaiting() == 2 ? "都到齐了，继续走啊" : "正在等候"));
						try {
							barrier.await();
						} catch (BrokenBarrierException e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};
			service.execute(runnale);
		}
		service.shutdown();
	}

}
