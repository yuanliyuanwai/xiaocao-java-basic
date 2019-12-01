package xiaocao.java.basic.design.pattern.builder;

public class Client {
	
	public static void main(String[] args) {
		Builder builder = new NormalBuilder();
		FoodManager manager = new FoodManager();
		manager.construct(builder);
		Food food = builder.getFood();
		food.show();
	}

}
