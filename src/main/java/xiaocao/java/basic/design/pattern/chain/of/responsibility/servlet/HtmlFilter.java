package xiaocao.java.basic.design.pattern.chain.of.responsibility.servlet;

public class HtmlFilter implements Filter {

	@Override
	public void doFilter(Request req, Response res, FilterChain fc) {
		req.setRequestString(req.getRequestString().replace("<", "[").replace(">", "]")+"---Html");
		fc.doFilter(req, res, fc);
		res.setResponseString(res.getResponseString() + "---Html");
	}

}
