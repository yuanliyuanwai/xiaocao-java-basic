package xiaocao.java.basic.design.pattern.observer.jdk;

import junit.framework.TestCase;

public class ObserverTest extends TestCase {
	
	private MockTimeSink sink;
	
	private MockTimeSource source;
	
	public ObserverTest(String name) {
		super(name);
	}
	
	public void setUp() {
		source = new MockTimeSource();
		sink = new MockTimeSink();
		source.addObserver(sink);
	}
	
	public void testTimeChange() {
		source.setTime(3, 4, 5);
		assertEquals(3, sink.getItsHours());
		assertEquals(4, sink.getItsMinues());
		assertEquals(5, sink.getItsSeconds());
	}

}
