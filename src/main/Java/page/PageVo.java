package page;

import cn.seczone.security.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Created by xiongshuaishuai@seczone.cn on 2017/9/11.
 */
public class PageVo<T> {

	@NotNull
	@Min(1)
	@ApiModelProperty(required = true, name = "pageIndex", value = "the target page which page numbers")
	private int pageIndex;

	@NotNull
	@Min(1)
	@Max(100)
	@ApiModelProperty(required = true, name = "pageSize", value = "min 1")
	private int pageSize;

	@ApiModelProperty(hidden = true)
	private int total;

	@ApiModelProperty(hidden = true)
	private int start;

	@ApiModelProperty(value = "sort order")
	private String order;

	@ApiModelProperty(value = "sort conditions")
	@Pattern(regexp = "^asc$|^desc$")
	private String sort;

	@ApiModelProperty(value = "过滤字段")
	@Size(max = 64)
	private String filter;

	@ApiModelProperty
	private List<T> data;

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private T queryCondition ;

    @ApiModelProperty(value = "total page numbers")
	private int pages ;
    @ApiModelProperty(value = "current page list numbers")
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

	public void setFuzzySearchFilter(){
		String fuzzySearchFilter = this.filter;
		if (Utils.isNotEmpty(fuzzySearchFilter)){
			this.filter = "%".concat(fuzzySearchFilter).concat("%");
		}
	}
}
