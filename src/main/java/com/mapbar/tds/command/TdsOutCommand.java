package com.mapbar.tds.command;

/**
 * Created by yinsihua on 2017/5/2.
 */
public class TdsOutCommand {

    private String velectagscanner;
    private String vvin;
    private String vcompany;
    private String vtjobno;
    private String vrelateman;
    private String vteam;
    private String voperator;

    public String getVelectagscanner() {
        return velectagscanner;
    }

    public void setVelectagscanner(String velectagscanner) {
        this.velectagscanner = velectagscanner;
    }

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

    public String getVtjobno() {
        return vtjobno;
    }

    public void setVtjobno(String vtjobno) {
        this.vtjobno = vtjobno;
    }

    public String getVrelateman() {
        return vrelateman;
    }

    public void setVrelateman(String vrelateman) {
        this.vrelateman = vrelateman;
    }

    public String getVteam() {
        return vteam;
    }

    public void setVteam(String vteam) {
        this.vteam = vteam;
    }

    public String getVoperator() {
        return voperator;
    }

    public void setVoperator(String voperator) {
        this.voperator = voperator;
    }

    @Override
    public String toString() {
        return "TdsOutCommand{" +
                "velectagscanner='" + velectagscanner + '\'' +
                ", vvin='" + vvin + '\'' +
                ", vcompany='" + vcompany + '\'' +
                ", vtjobno='" + vtjobno + '\'' +
                ", vrelateman='" + vrelateman + '\'' +
                ", vteam='" + vteam + '\'' +
                ", voperator='" + voperator + '\'' +
                '}';
    }
}
