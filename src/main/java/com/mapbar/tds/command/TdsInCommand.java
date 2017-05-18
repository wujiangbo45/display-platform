package com.mapbar.tds.command;

/**
 * Created by yinsihua on 2017/5/2.
 */
public class TdsInCommand extends BaseCommand {

    // VIN码
    private String vvin;
    // 制造公司代码
    private String vcompany;
    // 生产工厂代码
    private String vfactory;
    // 班次代码
    private String vteam;
    // 销售资源库代码
    private String vpropertywh;
    // 物理库代码
    private String vphysicalwh;
    // 仓库库区代码
    private String varea;
    // 质检员代码
    private String vscrutator;
    // 倒车司机代码
    private String vrelateman;
    // 操作员编号
    private String voperator;

    public String getVvin() {
        return vvin;
    }

    public void setVvin(String vvin) {
        this.vvin = vvin;
    }

    public String getVcompany() {
        return vcompany;
    }

    public void setVcompany(String vcompany) {
        this.vcompany = vcompany;
    }

    public String getVfactory() {
        return vfactory;
    }

    public void setVfactory(String vfactory) {
        this.vfactory = vfactory;
    }

    public String getVteam() {
        return vteam;
    }

    public void setVteam(String vteam) {
        this.vteam = vteam;
    }

    public String getVpropertywh() {
        return vpropertywh;
    }

    public void setVpropertywh(String vpropertywh) {
        this.vpropertywh = vpropertywh;
    }

    public String getVphysicalwh() {
        return vphysicalwh;
    }

    public void setVphysicalwh(String vphysicalwh) {
        this.vphysicalwh = vphysicalwh;
    }

    public String getVarea() {
        return varea;
    }

    public void setVarea(String varea) {
        this.varea = varea;
    }

    public String getVscrutator() {
        return vscrutator;
    }

    public void setVscrutator(String vscrutator) {
        this.vscrutator = vscrutator;
    }

    public String getVrelateman() {
        return vrelateman;
    }

    public void setVrelateman(String vrelateman) {
        this.vrelateman = vrelateman;
    }

    public String getVoperator() {
        return voperator;
    }

    public void setVoperator(String voperator) {
        this.voperator = voperator;
    }

    @Override
    public String toString() {
        return "TdsInCommand{" +
                "vvin='" + vvin + '\'' +
                ", vcompany='" + vcompany + '\'' +
                ", vfactory='" + vfactory + '\'' +
                ", vteam='" + vteam + '\'' +
                ", vpropertywh='" + vpropertywh + '\'' +
                ", vphysicalwh='" + vphysicalwh + '\'' +
                ", varea='" + varea + '\'' +
                ", vscrutator='" + vscrutator + '\'' +
                ", vrelateman='" + vrelateman + '\'' +
                ", voperator='" + voperator + '\'' +
                '}';
    }
}
