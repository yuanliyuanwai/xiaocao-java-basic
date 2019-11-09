package xiaocao.learn.java.basic.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Lock来实现Semaphore的功能
 * 这2个类有很多共同之处s
 * @author Administrator
 *
 */
public class SemaphoreOnLockDemo {
	
	private final Lock lock = new ReentrantLock();
	
	private final Condition permitsAvailable = lock.newCondition();
	
	private int permits;
	
	SemaphoreOnLockDemo(int permits) {
		lock.lock();
		try{
			this.permits = permits;
		} finally {
			lock.unlock();
		}
	}
	
	// 阻塞并直到 : permitsAvailable
	public void acquire() throws InterruptedException {
		lock.lock();
		try{
			while(permits <= 0) {
				permitsAvailable.await();
			}
			--permits;
		} finally {
			lock.unlock();
		}
	}
	
	public void release() {
		lock.lock();
		try{
			++permits;
			permitsAvailable.signal();
		} finally {
			lock.unlock();
		}
	}
	

}
