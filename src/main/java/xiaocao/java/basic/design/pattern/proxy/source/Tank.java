package xiaocao.java.basic.design.pattern.proxy.source;

import java.util.Random;

public class Tank implements Moveable {

	@Override
	public void move() {
		System.out.println("坦克在移动");
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
