package xiaocao.learn.java.basic.util;


import java.util.ArrayList;
import java.util.List;

/**
 * 分页的辅助对象，通过设置少量的值，自动计算其它的值
 * 用法一：首先通过构造函数构建分页对象，然后调用setList(List list) 方法，其中list是所有数据的集合，通过getPageList()来获取分页之后的集合
 * 用法二：首先通过构造函数构建分页对象，然后调用setTotalCount(int totalCount)方法，其中totalCount是总共多少条数据，通过setPageList(List list)来设置分页数据
 * @author Administrator
 *
 */
public class Pagination<T extends Object> {
	
	/**默认每页多少条数据*/
	public static final int DEFAULT_PAGE_SIZE = 10;
	/**
	 * 总共多少条数据:通过set进行设置
	 */
	private int totalCount;
	/**
	 * 总共多少页:自动计算
	 */
	private int pageCount;
	/**
	 * 当前第多少页
	 */
	private int pageIndex;
	/**
	 * 每页显示多少条数据
	 */
	private int pageSize;
	/**
	 * 开始索引值，从1开始
	 */
	private int beginIndex;
	/**
	 * 结束索引值
	 */
	private int endIndex;
	
	/**所有的数据*/
	private List<T> list = new ArrayList<T>();
	
	/**分页的数据*/
	private List<T> pageList = new ArrayList<T>();
	
	
	/** 屏蔽默认构造函数 */
	@SuppressWarnings("unused")
	private Pagination(){

	}

    /** 强制使用此构造函数，对pageIndex,pageSize进行初始化 */
	/**
	 */
    public Pagination(String index, String size) {
        int pageIndex = StringUtil.isNil(index) ? 1 : (Integer.parseInt(index) <= 0 ? 1 : Integer.parseInt(index));
        int pageSize = StringUtil.isNil(size) ? DEFAULT_PAGE_SIZE : (Integer.parseInt(size) < 0 ? DEFAULT_PAGE_SIZE : Integer.parseInt(size));
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

	/** 强制使用此构造函数，对pageIndex,pageSize进行初始化 */
	public Pagination(int pageIndex,int pageSize){
		this.pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	/**
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if(totalCount == 0){
			this.setPageCount(0);
			this.setPageIndex(0);
			this.setPageSize(0);
			this.setBeginIndex(0);
			this.setEndIndex(0);
		} else {
			this.setPageCount(totalCount % getPageSize() == 0 ? totalCount / getPageSize() : totalCount / getPageSize() + 1);
			this.setPageIndex(getPageIndex() > getPageCount() ? getPageCount() : getPageIndex());
			this.setBeginIndex((getPageIndex() - 1) * getPageSize() + 1);
			this.setEndIndex(getPageIndex() * getPageSize() > totalCount ? totalCount : getPageIndex() * getPageSize());
		}
		this.totalCount = totalCount;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	
	private void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	private void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getBeginIndex() {
		return beginIndex;
	}
	private void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	
	private void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		if(list == null || list.size() == 0) {
			setTotalCount(0);
		} else {
			setTotalCount(list.size());
			for(int i = beginIndex - 1; i < endIndex; i++) {
				pageList.add(list.get(i));
			}
		}
		this.list = list;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	
	private void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	/**
	 * 得到分页的数据集合
	 * @return
	 */
	public List<T> getPageList() {
		return pageList;
	}
	
	public void setPageList(List<T> list) {
		this.pageList = list;
	}
}
