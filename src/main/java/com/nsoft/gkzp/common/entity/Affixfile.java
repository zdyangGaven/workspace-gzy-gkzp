package com.nsoft.gkzp.common.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Affixfile implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(insertable=false)
    private Integer id;

    private String filecname;

    private String fileurl;

    private String submitman;

    private Date submittime;

    private static final long serialVersionUID = 1L;

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

    public String getSubmitman() {
        return submitman;
    }

    public void setSubmitman(String submitman) {
        this.submitman = submitman == null ? null : submitman.trim();
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", filecname=").append(filecname);
        sb.append(", fileurl=").append(fileurl);
        sb.append(", submitman=").append(submitman);
        sb.append(", submittime=").append(submittime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}