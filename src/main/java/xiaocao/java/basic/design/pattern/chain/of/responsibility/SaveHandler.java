package xiaocao.java.basic.design.pattern.chain.of.responsibility;

public class SaveHandler implements Handler {
	
	private Handler next;
	
	public SaveHandler(Handler handler) {
		this.next = handler;
	}

	@Override
	public void handleRequest(Request request) {
		if(request instanceof SaveRequest) {
			System.out.println("SaveHandler handler " + request.getClass().getSimpleName());
		} else {
			System.out.println("SaveHandler can't handle" + request.getClass().getSimpleName());
			if(next != null) {
				next.handleRequest(request);
			}
		}
		
	}

}
