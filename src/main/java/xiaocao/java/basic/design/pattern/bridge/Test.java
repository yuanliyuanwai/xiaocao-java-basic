package xiaocao.java.basic.design.pattern.bridge;

public class Test {
	
	public static void main(String[] args) {
		Peppery peppery = new Acid();
		Food food = new PigNoodle(peppery);
		System.out.println(food.getName());
	}

}
