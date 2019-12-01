package xiaocao.java.basic.design.pattern.decorator;

public abstract class Flavoring implements Food {
	
	protected Food food;
	
	public Flavoring(Food food) {
		this.food = food;
	}

	@Override
	public abstract double getPrice();
	

	@Override
	public abstract String getName();
	

}
