package xiaocao.java.basic.design.pattern.decorator;

public class Dumpling implements Food {

	@Override
	public double getPrice() {
		return 4;
	}

	@Override
	public String getName() {
		return "饺子";
	}

}
