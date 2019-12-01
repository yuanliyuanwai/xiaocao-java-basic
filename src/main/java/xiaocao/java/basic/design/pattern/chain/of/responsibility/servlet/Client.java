package xiaocao.java.basic.design.pattern.chain.of.responsibility.servlet;

public class Client {
	
	public static void main(String[] args) {
		String msg = "大家好:)，<script>,很黄，很暴力";
		Request req = new Request();
		req.setRequestString(msg);
		
		Response res = new Response();
		res.setResponseString("response");
		
		FilterChain fc = new FilterChain();
		Filter filter1 = new HtmlFilter();
		Filter filter2 = new SensitiveFilter();
		fc.addFilter(filter1).addFilter(filter2);
		fc.doFilter(req, res, fc);
		System.out.println(req.getRequestString());
		System.out.println(res.getResponseString());
		
		
	}

}
