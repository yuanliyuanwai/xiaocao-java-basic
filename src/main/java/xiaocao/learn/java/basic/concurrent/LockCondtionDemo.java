package xiaocao.learn.java.basic.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于显式锁和Condition的用法
 * 一个锁可以有多个Condition
 * Conditon.await = object.await
 * Condtion.signal = object.notify
 * Condtion.signalall = object.notifyAll
 * @author Administrator
 *
 */
public class LockCondtionDemo<T> {
	
	protected final Lock lock = new ReentrantLock();
	
	// 条件谓语
	private final Condition notFull = lock.newCondition();
	
	// 条件谓语
	private final Condition notEmpty = lock.newCondition();
	
	private final T[] items = (T[]) new Object[100];
	
	private int tail, head, count;
	
	public void put(T x) throws InterruptedException {
		lock.lock();
		try{
			while(count == items.length)
				notFull.await();
			items[tail] = x;
			if(++tail == items.length)
				tail = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public T take() throws InterruptedException {
		lock.lock();
		try{
			while(count == items.length)
				notEmpty.await();
			T x = items[head];
			items[head] = null;
			if(++head == items.length)
				head = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}

}
