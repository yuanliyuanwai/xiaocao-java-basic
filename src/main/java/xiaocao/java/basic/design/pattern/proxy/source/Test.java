package xiaocao.java.basic.design.pattern.proxy.source;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Tank t = new Tank();
		Moveable m = (Moveable)Proxy.getObjectProxy(Moveable.class, new TimeHandler(t));
		m.move();
	}

}
