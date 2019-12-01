package xiaocao.java.basic.design.pattern.visitor.acyclic;

public class HayesModem implements Modem {
	
	String configurationString = null;

	@Override
	public void dial(String pno) {
		
		
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void accept(ModemVisitor v) {
		try{
			HayesModemVisitor hv = (HayesModemVisitor)v;
			hv.visit(this);
		} catch (ClassCastException e) {
			
		}
	}

}
