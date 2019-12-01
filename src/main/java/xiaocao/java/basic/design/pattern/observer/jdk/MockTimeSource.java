package xiaocao.java.basic.design.pattern.observer.jdk;

import java.util.Observable;

public class MockTimeSource extends Observable {

	private int itsHours;

	private int itsMinutes;

	private int itsSeconds;

	public void setTime(int hours, int minutes, int seconds) {
		this.itsHours = hours;
		this.itsMinutes = minutes;
		this.itsSeconds = seconds;
		setChanged();
		notifyObservers();
	}

	public int getHours() {
		return itsHours;
	}

	public int getMinutes() {
		return itsMinutes;
	}

	public int getSeconds() {
		return itsSeconds;
	}

}
