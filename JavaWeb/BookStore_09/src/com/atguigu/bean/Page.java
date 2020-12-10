package com.atguigu.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Page<T> {
    private int pageNo; // 当前第几页
    private int totalPage; // 总页数
    private int totalCount; // 总记录数，数据库查询得到
    private int pageSize = 4; // 每页显示的记录数
    // 数据库中查询时的起始索引 select * from table limit index, size
    private int index;
    private boolean hasNext; // 是否有下一页
    private boolean hasPre; // 是否有上一页

    // 当前页面的项目url，因为分类可以用于不同的情况，比如用户请求图书列表或者管理员请求图书列表
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private List<T> pageData; // 当前页的数据

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo < 1 ? 1 : Math.min(pageNo, getTotalPage());
    }

    public int getTotalPage() {
        return totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize < 1 ? 4 : pageSize;
    }

    public int getIndex() {
        return (pageNo - 1) * pageSize;
    }

    public boolean isHasNext() {
        return pageNo < totalPage;
    }


    public boolean isHasPre() {
        return pageNo > 1;
    }


    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}
