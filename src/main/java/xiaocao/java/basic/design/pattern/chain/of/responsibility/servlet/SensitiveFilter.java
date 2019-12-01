package xiaocao.java.basic.design.pattern.chain.of.responsibility.servlet;

public class SensitiveFilter implements Filter {
	@Override
	public void doFilter(Request req, Response res,FilterChain fc) {
		req.setRequestString(req.getRequestString().replace("黄", "和谐").replace("暴力", "")+"---sensitive");
		fc.doFilter(req, res, fc);
		res.setResponseString (res.getResponseString() +"---sensitive");
	}

}
