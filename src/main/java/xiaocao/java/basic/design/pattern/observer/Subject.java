package xiaocao.java.basic.design.pattern.observer;

import java.util.Iterator;
import java.util.Vector;

public class Subject {
	
	private Vector itsObservers = new Vector();
	
	protected void notifyObservers() {
		Iterator i = itsObservers.iterator();
		while(i.hasNext()) {
			Observer observer = (Observer)i.next();
			observer.update();
		}
	}
	
	public void registerObserver(Observer observer) {
		itsObservers.add(observer);
	}

}
