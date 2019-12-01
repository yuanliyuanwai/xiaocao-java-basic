package xiaocao.java.basic.design.pattern.composite;

public class LightOnCommand implements Command {

	@Override
	public void action() {
		System.out.println("开灯");
	}

}
