package page;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author XieShaoping
 * @date 2019/12/11
 * @description
 */
public class PageInfoVo<T> extends PageInfo<T> {
    private int pageIndex;
    private List<T> data;

    public int getPageIndex() {
        return super.getPageNum();
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public PageInfoVo(List<T> list) {
        super(list, 8);
    }

    public List<T> getData() {
        return super.getList();
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
