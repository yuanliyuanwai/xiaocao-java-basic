package xiaocao.java.basic.design.pattern.state;

public class UnlockedTurnstileState implements TurnstileState {

	@Override
	public void coin(Turnstile t) {
		t.thankyou();
	}

	@Override
	public void pass(Turnstile t) {
		t.setLocked();
		t.lock();
	}
}
