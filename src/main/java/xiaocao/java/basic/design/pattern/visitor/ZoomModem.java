package xiaocao.java.basic.design.pattern.visitor;

public class ZoomModem implements Modem {
	
	int configurationValue = 0;

	@Override
	public void dial(String pno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hangup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(char c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public char recv() {
		return 0;
	}

	@Override
	public void accept(ModemVisitor v) {
		v.visit(this);
		
	}

}
