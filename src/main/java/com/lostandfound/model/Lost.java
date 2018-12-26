package com.lostandfound.model;

import java.util.Date;

public class Lost {
    private Integer lid;

    private String luid;

    private String limg;

    private String lname;

    private String lstatus;

    private Date ldate;

    private String lpos;

    private Integer ltid;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLuid() {
        return luid;
    }

    public void setLuid(String luid) {
        this.luid = luid == null ? null : luid.trim();
    }

    public String getLimg() {
        return limg;
    }

    public void setLimg(String limg) {
        this.limg = limg == null ? null : limg.trim();
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname == null ? null : lname.trim();
    }

    public String getLstatus() {
        return lstatus;
    }

    public void setLstatus(String lstatus) {
        this.lstatus = lstatus == null ? null : lstatus.trim();
    }

    public Date getLdate() {
        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    public String getLpos() {
        return lpos;
    }

    public void setLpos(String lpos) {
        this.lpos = lpos == null ? null : lpos.trim();
    }

    public Integer getLtid() {
        return ltid;
    }

    public void setLtid(Integer ltid) {
        this.ltid = ltid;
    }
}