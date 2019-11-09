package xiaocao.learn.java.basic.concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 错误的Timer行为尽量不要使用Timer
 * @author Administrator
 *
 */
public class OutOfTime {
	
	public static void main(String[] args) throws Exception {
		Timer timer = new Timer();
		timer.schedule(new ThrowTask() , 1);
		TimeUnit.SECONDS.sleep(1);
		timer.schedule(new ThrowTask() , 1);
		TimeUnit.SECONDS.sleep(5);
	}
	
	static class ThrowTask extends TimerTask {
		public void run() {
			throw new RuntimeException();
		}
	}

}
