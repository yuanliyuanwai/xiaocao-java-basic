package xiaocao.java.basic.design.pattern.builder;

public class GoldBuilder extends Builder {
	
	private Food goldFood;
	
	public GoldBuilder() {
		this.goldFood = new Food();
	}

	@Override
	public void buildHamb() {
		goldFood.add("GoldHamb", 13.5);
	}

	@Override
	public void buildCoke() {
		goldFood.add("GoldCole", 5.00);
		
	}

	@Override
	public void buildChip() {
		goldFood.add("GoldChip", 5.00);
		
	}

	@Override
	public Food getFood() {
		return goldFood;
	}

}
