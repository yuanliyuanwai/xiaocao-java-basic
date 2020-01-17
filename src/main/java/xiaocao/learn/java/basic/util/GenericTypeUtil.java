package xiaocao.learn.java.basic.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 参数化类型的辅助类
 * @author Administrator
 *
 */
public class GenericTypeUtil {
	
	/**
	 * 获取类里面的某个属性的参数化类型
	 * @param clazz
	 * @param parameter
	 * @return
	 */
	public static List<String> getParameterGenericType(Class<?> clazz, String parameter) {
		List<String> list = new ArrayList<String>();
		try {
			Field field = clazz.getDeclaredField(parameter);
			Type type = field.getGenericType();
			if(type instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType)type;
				Type[] actualTypes = parameterizedType.getActualTypeArguments();
				for(Type actualType : actualTypes) {
					if(actualType instanceof Class) {
						list.add(((Class<?>) actualType).getName());
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
