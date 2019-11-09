package xiaocao.learn.java.basic.concurrent;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 通过中断是取消线程最合理的方式
 * 大多数的阻塞式的函数都会响应中断：清楚中断状态，抛出中断异常
 * @author Administrator
 *
 */
public class PrimeProducer extends Thread {
	
	private final BlockingQueue<BigInteger> queue;
	
	PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try{
			BigInteger p = BigInteger.ONE;
			while(Thread.currentThread().isInterrupted()) {
				queue.put(p = p.nextProbablePrime());
			}
		} catch (InterruptedException e) {
			/* 允许线程退出*/
		}
	}
	
	public void cancel() {
		interrupt();
	}

}
