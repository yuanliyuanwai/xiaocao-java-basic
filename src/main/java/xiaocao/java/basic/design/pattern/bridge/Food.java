package xiaocao.java.basic.design.pattern.bridge;

public abstract class Food {
	
	protected Peppery peppery;
	
	public Food(Peppery peppery) {
		this.peppery = peppery;
	}
	
	public abstract String getName();

}
