package com.mapbar.report;

import org.bson.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: wujiangbo
 * @Create: 2018/12/18 下午3:25
 */
public class DictInfo implements Serializable {
    private long dictCode;
    private String name;

    public long getDictCode() {
        return dictCode;
    }

    public void setDictCode(long dictCode) {
        this.dictCode = dictCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictInfo dictInfo = (DictInfo) o;
        return dictCode == dictInfo.dictCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dictCode);
    }

    public Document toDocument() {
        return new Document()
                .append("dictCode", this.dictCode)
                .append("name", this.name);
    }
}
