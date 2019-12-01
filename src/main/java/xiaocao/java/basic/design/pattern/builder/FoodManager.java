package xiaocao.java.basic.design.pattern.builder;

public class FoodManager {
	
	public void construct(Builder builder) {
		builder.buildHamb();
		builder.buildCoke();
		builder.buildChip();
	}

}
