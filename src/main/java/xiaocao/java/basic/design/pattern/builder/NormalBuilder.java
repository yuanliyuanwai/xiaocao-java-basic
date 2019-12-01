package xiaocao.java.basic.design.pattern.builder;

public class NormalBuilder extends Builder {
	
	private Food normalFood = null;
	
	public NormalBuilder() {
		this.normalFood = new Food();
	}

	@Override
	public void buildHamb() {
		// TODO Auto-generated method stub
		normalFood.add("NormalHamb", 3.00);
	}

	@Override
	public void buildCoke() {
		normalFood.add("NormalCoke", 2.00);
		
	}

	@Override
	public void buildChip() {
		normalFood.add("NormalChip", 1.00);
	}

	@Override
	public Food getFood() {
		return normalFood;
	}

}
