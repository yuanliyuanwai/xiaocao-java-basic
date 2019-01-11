package xiaocao.java.basic.jdk8.feature;

import java.util.function.Supplier;

/**
 * 
 * @ClassName: InterfaceStaticDefaultMethod
 * @Description: 接口的静态方法和默认方法 默认方法：实现类可以重写也可以不重写
 * @author zhengchong.wan
 * @date 2019年1月11日 下午7:24:54
 *
 */
public class InterfaceStaticDefaultMethod {

	private interface Defaulable {
		// Interfaces now allow default methods, the implementer may or
		// may not implement (override) them.
		default String notRequired() {
			return "Default implementation";
		}
	}

	private static class DefaultableImpl implements Defaulable {

	}

	private static class OverridableImpl implements Defaulable {

		@Override
		public String notRequired() {
			return "Overridden implementation";
		}
	}

	private interface DefaulableFactory {
		// Interfaces now allow static methods
		static Defaulable create(Supplier<Defaulable> supplier) {
			return supplier.get();
		}
	}

	public static void main(String[] args) {
		Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
		System.out.println(defaulable.notRequired());

		defaulable = DefaulableFactory.create(OverridableImpl::new);
		System.out.println(defaulable.notRequired());
	}

}
