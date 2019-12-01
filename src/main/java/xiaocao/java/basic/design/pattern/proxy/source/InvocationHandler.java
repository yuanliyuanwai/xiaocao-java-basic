package xiaocao.java.basic.design.pattern.proxy.source;

import java.lang.reflect.Method;

public interface InvocationHandler {
	
	void invoike(Object object, Method m);

}
