package xiaocao.java.basic.design.pattern.proxy.source;

import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {
	
	private Object target;
	
	public TimeHandler(Object target) {
		this.target = target;
	}

	@Override
	public void invoike(Object object, Method m) {
		long begin = System.currentTimeMillis();
		try {
			m.invoke(target);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("运行时间:" + (end - begin));
	}

}
