package xiaocao.java.basic.jdk8.feature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * jdk8提供了获取参数名的支持不过编译的时候需要开启-parameters参数
 * 如果是maven也需要开启同样的配置
 * <plugin>
 *    <groupId>org.apache.maven.plugins</groupId>
 *    <artifactId>maven-compiler-plugin</artifactId>
 *    <version>3.1</version>
 *    <configuration>
 *        <compilerArgument>-parameters</compilerArgument>
 *        <source>1.8</source>
 *        <target>1.8</target>
 *    </configuration>
 *</plugin>
 * @author zhengchong.wan
 *
 */
public class ParameterNames {
	
	public static void main(String[] args) throws Exception {
		Method method = ParameterNames.class.getMethod( "main", String[].class );
        for( final Parameter parameter: method.getParameters() ) {
            System.out.println( "Parameter: " + parameter.getName() );
        }
	}

}
