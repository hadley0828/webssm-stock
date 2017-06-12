package com.quantour.ssm.dto.stockRate;

/**
 * Created by zhangzy on 2017/6/12.
 * 成长能力
 */
public class Basic_growDTO {

    String code;    //代码
    String name;    //名称
    String mbrg;    //主营业务收入增长率
    String nprg;    //净利润增长率
    String nav;     //净资产增长率
    String targ;    //总资产增长率
    String epsg;    //每股收益增长率
    String seg;     //股东收益增长率

    public Basic_growDTO(){
        super();
    }

    @Override
    public String toString() {
        return "Basic_growDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", mbrg='" + mbrg + '\'' +
                ", nprg='" + nprg + '\'' +
                ", nav='" + nav + '\'' +
                ", targ='" + targ + '\'' +
                ", epsg='" + epsg + '\'' +
                ", seg='" + seg + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbrg() {
        return mbrg;
    }

    public void setMbrg(String mbrg) {
        this.mbrg = mbrg;
    }

    public String getNprg() {
        return nprg;
    }

    public void setNprg(String nprg) {
        this.nprg = nprg;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getTarg() {
        return targ;
    }

    public void setTarg(String targ) {
        this.targ = targ;
    }

    public String getEpsg() {
        return epsg;
    }

    public void setEpsg(String epsg) {
        this.epsg = epsg;
    }

    public String getSeg() {
        return seg;
    }

    public void setSeg(String seg) {
        this.seg = seg;
    }


}
