package xiaocao.java.basic.design.pattern.state;

public class LockedTurnstileState implements TurnstileState {

	@Override
	public void coin(Turnstile t) {
		t.setUnlocked();
		t.unlock();
	}

	@Override
	public void pass(Turnstile t) {
		t.alarm();
	}

}
