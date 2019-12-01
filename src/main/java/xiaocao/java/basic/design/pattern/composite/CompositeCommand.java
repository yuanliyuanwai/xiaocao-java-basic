package xiaocao.java.basic.design.pattern.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeCommand implements Command {
	
	private List<Command> commands = new ArrayList<Command>();
	
	public void add(Command c) {
		commands.add(c);
	}

	@Override
	public void action() {
		for(int i = 0; i < commands.size(); i++) {
			commands.get(i).action();
		}
	}

}
