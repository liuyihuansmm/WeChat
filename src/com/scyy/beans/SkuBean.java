package com.scyy.beans;

/**
 * Created by LYH on 2016/3/2.
 */
public class SkuBean {

    private String descr;       //品名
    private String susrsyx46;   //生产厂商
    private String susrsyx41;   //规格
    private String susrsyx28;   //国药准字

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getSusrsyx46() {
        return susrsyx46;
    }

    public void setSusrsyx46(String susrsyx46) {
        this.susrsyx46 = susrsyx46;
    }

    public String getSusrsyx41() {
        return susrsyx41;
    }

    public void setSusrsyx41(String susrsyx41) {
        this.susrsyx41 = susrsyx41;
    }

    public String getSusrsyx28() {
        return susrsyx28;
    }

    public void setSusrsyx28(String susrsyx28) {
        this.susrsyx28 = susrsyx28;
    }

    @Override
    public String toString() {
        return      "品名："+descr+"\n"
                +   "产地："+susrsyx46+"\n"
                +   "规格："+susrsyx41+"\n"
                +   "国药准字："+susrsyx28+"\n";

    }
}
