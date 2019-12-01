package xiaocao.java.basic.design.pattern.chain.of.responsibility;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Handler handler1 = new HelpHandler(null);
		Handler handler2 = new PrintHandler(handler1);
		Handler handler3 = new SaveHandler(handler2);
		
		handler3.handleRequest(new HelpRequest());
		handler3.handleRequest(new PrintRequest());
		handler3.handleRequest(new SaveRequest());
	}

}
