package xiaocao.java.basic.design.pattern.decorator;

public class Sweet extends Flavoring {

	public Sweet(Food food) {
		super(food);
	}

	@Override
	public double getPrice() {
		double basePrice = this.food.getPrice();
		return basePrice + 0.3;
	}

	@Override
	public String getName() {
		String baseName = this.food.getName();
		return "甜" + baseName;
	}

}
