package xiaocao.java.basic.design.pattern.visitor.acyclic;

public class UnixModemConfigurator implements ModemVisitor, HayesModemVisitor, ZoomModemVisitor, ErnieModemVisitor {

	@Override
	public void visit(ErnieModem m) {
		m.configurationString = "C is too slow";
	}

	@Override
	public void visit(ZoomModem m) {
		m.configurationValue = 42;
		
	}

	@Override
	public void visit(HayesModem m) {
		m.configurationString = "&s1=4&D=3";
		
	}

}
