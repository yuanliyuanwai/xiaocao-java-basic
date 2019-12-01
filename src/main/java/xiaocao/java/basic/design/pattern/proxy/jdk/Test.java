package xiaocao.java.basic.design.pattern.proxy.jdk;

import java.lang.reflect.Proxy;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cat cat = new Cat();
		Animal proxy = (Animal)Proxy.newProxyInstance(cat.getClass().getClassLoader(), cat.getClass().getInterfaces(), new ShoutHandler(cat));
		proxy.shout();

	}

}
