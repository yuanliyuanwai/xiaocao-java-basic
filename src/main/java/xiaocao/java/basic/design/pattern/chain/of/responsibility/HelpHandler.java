package xiaocao.java.basic.design.pattern.chain.of.responsibility;

public class HelpHandler implements Handler {
	
	private Handler next;
	
	public HelpHandler(Handler handler) {
		this.next = handler;
	}

	@Override
	public void handleRequest(Request request) {
		if(request instanceof HelpRequest) {
			System.out.println("HelpHander handler " + request.getClass().getSimpleName());
		} else {
			System.out.println("HelpHandler can't handle" + request.getClass().getSimpleName());
			if(next != null) {
				next.handleRequest(request);
			}
		}
		
	}

}
