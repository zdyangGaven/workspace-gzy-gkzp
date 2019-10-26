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

    private String localname;

    private Integer parentid;

    private String fileurl;

    private Integer filesize;

    private Integer filelevel;

    private Integer secrettype;

    private Integer filelife;

    private Date filedate;

    private Integer filetype;

    private Integer fileattrib;

    private Integer filedept;

    private Integer sourcetype;

    private Integer replytype;

    private String submitman;

    private Date submittime;

    private Integer backuptype;

    private Integer nlanguage;

    private Integer accesscount;

    private Integer checkstatus;

    private String subparentid;

    private Date lastaccesstime;

    private String openusers;

    private Integer writegroup;

    private Integer orderno;

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

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname == null ? null : localname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl == null ? null : fileurl.trim();
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public Integer getFilelevel() {
        return filelevel;
    }

    public void setFilelevel(Integer filelevel) {
        this.filelevel = filelevel;
    }

    public Integer getSecrettype() {
        return secrettype;
    }

    public void setSecrettype(Integer secrettype) {
        this.secrettype = secrettype;
    }

    public Integer getFilelife() {
        return filelife;
    }

    public void setFilelife(Integer filelife) {
        this.filelife = filelife;
    }

    public Date getFiledate() {
        return filedate;
    }

    public void setFiledate(Date filedate) {
        this.filedate = filedate;
    }

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public Integer getFileattrib() {
        return fileattrib;
    }

    public void setFileattrib(Integer fileattrib) {
        this.fileattrib = fileattrib;
    }

    public Integer getFiledept() {
        return filedept;
    }

    public void setFiledept(Integer filedept) {
        this.filedept = filedept;
    }

    public Integer getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(Integer sourcetype) {
        this.sourcetype = sourcetype;
    }

    public Integer getReplytype() {
        return replytype;
    }

    public void setReplytype(Integer replytype) {
        this.replytype = replytype;
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

    public Integer getBackuptype() {
        return backuptype;
    }

    public void setBackuptype(Integer backuptype) {
        this.backuptype = backuptype;
    }

    public Integer getNlanguage() {
        return nlanguage;
    }

    public void setNlanguage(Integer nlanguage) {
        this.nlanguage = nlanguage;
    }

    public Integer getAccesscount() {
        return accesscount;
    }

    public void setAccesscount(Integer accesscount) {
        this.accesscount = accesscount;
    }

    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }

    public String getSubparentid() {
        return subparentid;
    }

    public void setSubparentid(String subparentid) {
        this.subparentid = subparentid == null ? null : subparentid.trim();
    }

    public Date getLastaccesstime() {
        return lastaccesstime;
    }

    public void setLastaccesstime(Date lastaccesstime) {
        this.lastaccesstime = lastaccesstime;
    }

    public String getOpenusers() {
        return openusers;
    }

    public void setOpenusers(String openusers) {
        this.openusers = openusers == null ? null : openusers.trim();
    }

    public Integer getWritegroup() {
        return writegroup;
    }

    public void setWritegroup(Integer writegroup) {
        this.writegroup = writegroup;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }
}