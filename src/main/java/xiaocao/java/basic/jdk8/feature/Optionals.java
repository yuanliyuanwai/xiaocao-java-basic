package xiaocao.java.basic.jdk8.feature;

import java.util.Optional;

/**
 * jdk8引入Optional来解决空指针异常
 * @author zhengchong.wan
 *
 */
public class Optionals {
	
	public static void main(String[] args) {
		optinalWhenNull();
		System.out.println("-----------------------");
		optinalWhenNotNull();
	}
	
	public static void optinalWhenNull() {
		Optional<String> fullName = Optional.ofNullable( null );
		System.out.println( "Full Name is set? " + fullName.isPresent() );        
		System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) ); 
		System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
	}
	
	public static void optinalWhenNotNull() {
		Optional< String > firstName = Optional.of( "Tom" );
		System.out.println( "First Name is set? " + firstName.isPresent() );        
		System.out.println( "First Name: " + firstName.orElseGet( () -> "[none]" ) ); 
		System.out.println( firstName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
		System.out.println();
	}
	
	

}
