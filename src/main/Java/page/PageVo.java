package page;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created by xiongshuaishuai@seczone.cn on 2017/9/11.
 */
public class PageVo<T> {


	private int pageIndex;


	private int pageSize;


	private int total;


	private int start;


	private String order;


	private String sort;

	private String filter;


	private List<T> data;


	@JsonIgnore
	private T queryCondition ;


	private int pages ;

	private int size ;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(T queryCondition) {
		this.queryCondition = queryCondition;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int pageIndex, int pageSize) {
		this.start = (pageIndex - 1) * pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public PageVo(int pageIndex, int pageSize, String sort, String order, String filter) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.sort = sort;
		this.order = order;
		this.filter = filter;
		this.start = countStart(pageIndex, pageSize);
	}

	public PageVo(List<T> list) {
		if (list instanceof Page) {
			Page<T> page = (Page<T>) list;
			this.pageIndex = page.getPageNum() ;
			this.pageSize = page.getPageSize();
			this.total = ((Long) page.getTotal()).intValue();
			// 总页数
			this.pages = page.getPages();
			this.data = page;
			//当前页的数量 <= pageSize
			this.size = page.size();
		}
	}

	public PageVo() {}

	private int countStart(int pageIndex, int pageSize) {
		return (pageIndex - 1) * pageSize;
	}

}
