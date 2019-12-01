package xiaocao.java.basic.design.pattern.chain.of.responsibility;

public class PrintHandler implements Handler {
	
	private Handler next;
	
	public PrintHandler(Handler handler) {
		this.next = handler;
	}

	@Override
	public void handleRequest(Request request) {
		if(request instanceof PrintRequest) {
			System.out.println("PrintHandler handler " + request.getClass().getSimpleName());
		} else {
			System.out.println("PrintHandler can't handle" + request.getClass().getSimpleName());
			if(next != null) {
				next.handleRequest(request);
			}
		}
		
	}

}
