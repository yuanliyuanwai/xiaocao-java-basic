package xiaocao.java.basic.design.pattern.visitor;

public interface Modem {
	
	void dial(String pno);
	
	void hangup();
	
	void send(char c);
	
	char recv();
	
	void accept(ModemVisitor v);
	
	

}
