package xiaocao.java.basic.design.pattern.bridge;

public class PigNoodle extends Food {

	public PigNoodle(Peppery peppery) {
		super(peppery);
	}

	@Override
	public String getName() {
		return peppery.style() + "猪肉面";
	}

}
