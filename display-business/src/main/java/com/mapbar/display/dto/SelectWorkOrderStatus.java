package com.mapbar.display.dto;

import java.math.BigDecimal;

/**
 * @Author: wujiangbo
 * @Create: 2017/09/30 下午1:37
 */
public class SelectWorkOrderStatus {

    private BigDecimal num;
    private int sortType;

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }
}
