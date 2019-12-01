package xiaocao.java.basic.design.pattern.decorator;

public class Acid extends Flavoring {

	public Acid(Food food) {
		super(food);
	}

	@Override
	public double getPrice() {
		double basePrice = food.getPrice();
		return basePrice + 0.2;
	}

	@Override
	public String getName() {
		String baseName = food.getName();
		return "酸" + baseName;
	}
	
	

}
