package xiaocao.java.basic.design.pattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class MockTimeSink implements Observer {
	
	private int itsHours;
	
	private int itsMinues;
	
	private int itsSeconds;
	

	public int getItsHours() {
		return itsHours;
	}

	public int getItsMinues() {
		return itsMinues;
	}

	public int getItsSeconds() {
		return itsSeconds;
	}

	@Override
	public void update(Observable o, Object arg) {
		MockTimeSource itsSource = (MockTimeSource)o;
		itsHours = itsSource.getHours();
		itsMinues = itsSource.getMinutes();
		itsSeconds = itsSource.getSeconds();
	}


}
