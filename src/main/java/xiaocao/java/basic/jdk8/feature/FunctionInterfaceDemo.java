package xiaocao.java.basic.jdk8.feature;

/**
 * 
 * @ClassName: FunctionInterfaceDemo 
 * @Description: 函数接口
 *   函数接口就是只有一个方法的接口（默认方法和静态方法除外），函数接口可以隐式转换为Lambda表达式  
 * @author zhengchong.wan
 * @date 2019年1月11日 下午7:24:00
 *
 */
@FunctionalInterface
public interface FunctionInterfaceDemo {
	
	void method();
	
	default void defaultMethod() {
	}
	
	public static void main(String[] args) {
		print(() -> System.out.println("haha"));
	}
	
	public static void print(FunctionInterfaceDemo demo) {
		demo.method();
	}

}
