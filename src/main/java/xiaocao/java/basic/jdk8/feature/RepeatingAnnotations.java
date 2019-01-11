package xiaocao.java.basic.jdk8.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: RepeatingAnnotations 
 * @Description: 重复注解
 * @author zhengchong.wan
 * @date 2019年1月11日 下午7:22:14
 *
 */
public class RepeatingAnnotations {
	
	@Target(ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
		Filter[] value();
    }
	
	@Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable(Filters.class )
    public @interface Filter {
        String value();
    };
    
    @Filter( "filter1" )
    @Filter( "filter2" )
    public interface Filterable {        
    }
    
    public static void main(String[] args) {
        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
            System.out.println( filter.value() );
        }
    }

}
