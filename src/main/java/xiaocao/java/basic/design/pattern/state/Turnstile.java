package xiaocao.java.basic.design.pattern.state;

public class Turnstile {
	
	private static TurnstileState lockedState = new LockedTurnstileState();
	
	private static TurnstileState unlockedState = new UnlockedTurnstileState();
	
	private TurnstileController turnstileController;
	
	private TurnstileState state = lockedState;
	
	public Turnstile(TurnstileController action) {
		this.turnstileController = action;
	}
	
	public void coin() {
		state.coin(this);
	}
	
	public void pass() {
		state.pass(this);
	}
	
	public void setLocked() {
		state = lockedState;
	}
	
	public void setUnlocked() {
		state = unlockedState;
	}
	
	public boolean isLocked() {
		return state == lockedState;
	}
	
	public boolean isUnlocked() {
		return state == unlockedState;
	}
	
	void thankyou() {
		turnstileController.tankyou();
	}
	
	void alarm() {
		turnstileController.alarm();
	}
	
	void lock() {
		turnstileController.lock();
	}
	
	void unlock() {
		turnstileController.unlock();
	}
	

}
