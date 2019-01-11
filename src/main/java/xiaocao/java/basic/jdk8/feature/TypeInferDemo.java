package xiaocao.java.basic.jdk8.feature;

/**
 * 
 * @ClassName: TypeInferDemo 
 * @Description: jdk8编译器在类型推断方面有了很大的提升
 * @author zhengchong.wan
 * @date 2019年1月11日 下午7:19:23
 *
 */
public class TypeInferDemo {
	
	public static class Value< T > {
	    public static< T > T defaultValue() { 
	        return null; 
	    }

	    public T getOrDefault( T value, T defaultValue ) {
	        return ( value != null ) ? value : defaultValue;
	    }
	}
	
	public static void main(String[] args) {
        final Value< String > value = new Value<>();
        // before 1.8
        //String str = value.getOrDefault( "22", Value.<String>defaultValue() );
        // now in 1.8
        String str = value.getOrDefault( "22", Value.defaultValue() );
        System.out.println(str);
    }

}
