package xiaocao.java.basic.jdk8.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @ClassName: ExpandAnnotation 
 * @Description: 扩展的注解
 *     Java 8拓宽了注解的应用场景
 *     注解几乎可以使用在任何元素上：局部变量、接口类型、超类和接口实现类，甚至可以用在函数的异常定义
 * @author zhengchong.wan
 * @date 2019年1月11日 下午7:23:20
 *
 */
public class ExpandAnnotation {
	
	@Retention( RetentionPolicy.RUNTIME )
    @Target( {ElementType.TYPE_USE, ElementType.TYPE_PARAMETER } )
    public @interface NonEmpty {  
		
    }
	
	public static class Holder< @NonEmpty T > extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {            
        }
    }
	
	@SuppressWarnings( "unused" )
    public static void main(String[] args) {
        final Holder<String> holder = new @NonEmpty Holder<String>();        
        @NonEmpty Collection< @NonEmpty String > strings = new ArrayList<>();        
    }
	

}
