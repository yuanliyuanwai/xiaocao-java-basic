package xiaocao.java.basic.design.pattern.memento;

public class Caretaker {
	
	private MementoIF memento;
	
	public MementoIF retrieveMemento() {
		return this.memento;
	}
	
	public void saveMemento(MementoIF memento) {
		this.memento = memento;
	}

}
