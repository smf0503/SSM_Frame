package cn.smf.until;

import cn.smf.entity.User;

import java.util.List;

/**
 * Created by ASUS on 2017/9/23.
 * 承载页面上数据的一个综合体
 * 泛型数据  本页面的真实数据
 * pageIndex 当前页码
 * pageSize  每页显示的记录数   每页的数据量
 * totalPages 总页数
 * totalRecords  总记录数
 */
public class UserPage {
    private int pageIndex;
    private int pageSize;
    private int totalPages;
    private int totalRecords;
    private List<User> list;

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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
