package xiaocao.java.basic.design.pattern.memento;

public class Originator {
	
	private String state;
	
	public Originator() {
		
	}
	
	public MementoIF createMemento() {
		return new Memento(this.state);
	}
	
	public void restoreMemento(MementoIF memento) {
		Memento a = (Memento)memento;
		this.setState(a.getState());
	}
	
	public void setState(String state) {
		this.state = state;
		System.out.println("state= " + state);
	}
	
	public String getState() {
		return this.state;
	}
	
	
	protected class Memento implements MementoIF {
		
		private String saveState;
		
		private Memento(String someState) {
			saveState = someState;
		}
		
		private void setState(String someState) {
			saveState = someState;
		}
		
		private String getState() {
			return saveState;
		}
		
	}

}
