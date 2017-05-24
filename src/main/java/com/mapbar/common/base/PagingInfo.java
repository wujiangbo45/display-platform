package com.mapbar.common.base;

import java.util.List;

/**
 * General PagingData POJO
 *
 * @author chenjc
 * @date 2016-03-02
 * @modify
 * @copyright Navi Tsp
 */
public class PagingInfo<T> {

    /**
     * 查询数据总数
     */
    private long total;

    /**
     * 总页数
     */
    //private long totalPages;

    private long page_total;

    /**
     * 当前页数据集合
     */
    private List<T> list;

    public PagingInfo() {
    }

    /**
     * @param total 查询数据总数
     * @param list  当前页数据集合
     */
    public PagingInfo(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    /**
     * 获取查询数据总数
     */
    public long getTotal() {
        return total;
    }

    /**
     * 设置查询数据总数
     *
     * @param total 查询数据总数
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * 获取当前页数据集合
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置当前页数据集合
     *
     * @param list 当前页数据集合
     */
    public void setList(List<T> list) {
        this.list = list;
    }

  /*  public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }*/

    public long getPage_total() {
        return page_total;
    }

    public void setPage_total(long page_total) {
        this.page_total = page_total;
    }
}
