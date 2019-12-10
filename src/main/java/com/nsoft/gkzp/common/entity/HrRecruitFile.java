package com.nsoft.gkzp.common.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class HrRecruitFile implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(insertable=false)
    private Integer id;

    private String filecname;//名称

    private String fileurl;//路径

    private Date submittime;//提交时间

    private Integer loginuserid;//提交人

    private Integer syncstatus;//数据同步

    private Integer syncfile;//文件同步

    @Override
    public String toString() {
        return "HrRecruitFile{" +
                "id=" + id +
                ", filecname='" + filecname + '\'' +
                ", fileurl='" + fileurl + '\'' +
                ", submittime=" + submittime +
                ", loginuserid=" + loginuserid +
                ", syncstatus=" + syncstatus +
                ", syncfile=" + syncfile +
                '}';
    }

    public Integer getSyncstatus() {
        return syncstatus;
    }

    public void setSyncstatus(Integer syncstatus) {
        this.syncstatus = syncstatus;
    }

    public Integer getSyncfile() {
        return syncfile;
    }

    public void setSyncfile(Integer syncfile) {
        this.syncfile = syncfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilecname() {
        return filecname;
    }

    public void setFilecname(String filecname) {
        this.filecname = filecname == null ? null : filecname.trim();
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl == null ? null : fileurl.trim();
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public Integer getLoginuserid() {
        return loginuserid;
    }

    public void setLoginuserid(Integer loginuserid) {
        this.loginuserid = loginuserid;
    }
}