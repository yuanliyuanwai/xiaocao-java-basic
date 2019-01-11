package xiaocao.java.basic.jdk8.feature;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @ClassName: LambdaDemo 
 * @Description: Lambda表达式
 *     允许我们将函数当成参数传递给某个方法，用来替代匿名内部类
 * @author zhengchong.wan
 * @date 2019年1月11日 下午7:26:17
 *
 */
public class LambdaDemo {

	public static void main(String[] args) {
		// Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println(e));
		/*
		 * Arrays.asList( "a", "b", "d" ).forEach((String e) -> System.out.println(e));
		 * Arrays.asList("a", "b", "d" ).forEach(e -> { System.out.print(e);
		 * System.out.print(e); });
		 */

		// Lambda表达式可以引用类成员和局部变量（隐式得转换成final的）
		/*
		 * String separator = ","; Arrays.asList( "a", "b", "d" ).forEach((String e) ->
		 * System.out.print(e + separator));
		 */

		final String separator = ",";
		Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator));
	}

	// Lambda表达式有返回值，返回值的类型也由编译器推理得出。如果Lambda表达式中的语句块只有一行，则可以不用使用return语句
	// 下列两个代码片段效果相同
	public void returnValueUseCase() {
		List<String> list = Arrays.asList("d", "a", "b");
		list.sort((e1, e2) -> e1.compareTo(e2));
		list.forEach(e -> System.out.println(e));

		Arrays.asList("d", "a", "b").sort((e1, e2) -> {
			int result = e1.compareTo(e2);
			return result;
		});
	}

}
