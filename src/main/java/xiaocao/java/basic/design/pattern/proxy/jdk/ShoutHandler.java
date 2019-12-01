package xiaocao.java.basic.design.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ShoutHandler implements InvocationHandler {
	
	private  Object target;
	
	public ShoutHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("动物准备叫");
		Object result = method.invoke(target, args);
		System.out.println(result);
		System.out.println("动物吼叫完成");
		return result;
	}

}
