package com.mapbar.report;

import org.bson.Document;

import java.io.Serializable;

/**
 * @Author: wujiangbo
 * @Create: 2018/12/18 下午3:31
 */
public class TerminalLocation implements Serializable {
    private long tId;
    private int lat;
    private int lon;
    private long tileNumber;
    private long dictCode;
    private String dictName;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public long getDictCode() {
        return dictCode;
    }

    public void setDictCode(long dictCode) {
        this.dictCode = dictCode;
    }

    public long getTileNumber() {
        return tileNumber;
    }

    public void setTileNumber(long tileNumber) {
        this.tileNumber = tileNumber;
    }

    public long gettId() {
        return tId;
    }

    public void settId(long tId) {
        this.tId = tId;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public Document toDocument(){
        /**
         * private long tId;
         *     private int lat;
         *     private int lon;
         *     private long tileNumber;
         *     private long dictCode;
         */
        return new Document().append("lat",this.lat)
                .append("lon",this.lon)
                .append("dictCode",this.dictCode)
                .append("dictName",this.dictName);
    }
}
