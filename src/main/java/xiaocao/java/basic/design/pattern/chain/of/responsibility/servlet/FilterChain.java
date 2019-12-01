package xiaocao.java.basic.design.pattern.chain.of.responsibility.servlet;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {
	
	private List<Filter> filters = new ArrayList<Filter>();
	
	private int index;
	
	public FilterChain addFilter(Filter filter) {
		filters.add(filter);
		return this;
	}
	
	public List<Filter> getFilters() {
		return filters;
	}
	
	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	@Override
	public void doFilter(Request req, Response res, FilterChain fc) {
		if(index == filters.size()) return;
		Filter f = filters.get(index++);
		f.doFilter(req, res, fc);
	}

}
