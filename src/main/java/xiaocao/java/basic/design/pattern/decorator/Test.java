package xiaocao.java.basic.design.pattern.decorator;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Food food = new Acid(new Sweet(new Noodle()));
		System.out.println(food.getName());
		System.out.println(food.getPrice());
		

	}

}
