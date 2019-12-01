package xiaocao.java.basic.design.pattern.chain.of.responsibility.servlet;

public interface Filter {
	
	void doFilter(Request req, Response res, FilterChain fc);

}
