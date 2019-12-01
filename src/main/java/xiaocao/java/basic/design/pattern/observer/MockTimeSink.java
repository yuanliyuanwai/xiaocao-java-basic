package xiaocao.java.basic.design.pattern.observer;

public class MockTimeSink implements Observer {
	
	private int itsHours;
	
	private int itsMinues;
	
	private int itsSeconds;
	
	private TimeSource itsSource;
	
	public MockTimeSink(TimeSource source) {
		itsSource = source;
	}

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
	public void update() {
		itsHours = itsSource.getHours();
		itsMinues = itsSource.getMinutes();
		itsSeconds = itsSource.getSeconds();
	}

}
