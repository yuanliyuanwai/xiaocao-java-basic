package xiaocao.java.basic.design.pattern.bridge;

public class SheepNoodle extends Food{

	public SheepNoodle(Peppery peppery) {
		super(peppery);
	}

	@Override
	public String getName() {
		return "羊肉面";
	}

}
