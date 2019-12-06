package com.nsoft.gkzp.notice.entity;

import com.nsoft.gkzp.common.entity.FileVo;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HrRecruitArticle implements Serializable {

    @Id
    private Integer id;

    private Integer orderno;//排序字段

    private Integer type;//分类

    private String title;//标题

    private String description;//简介

    private String dept;//部门

    private String source;//来源

    private Date publishtime;//发布时间

    private String author;//作者

    private Integer clicktimes;//阅读次数

    private Integer status;//状态

    private String submitman;//提交人

    private Date submittime;//提交时间

    private Date modifytime;//修改时间

    private String content;//内容

    private String affixfile;//附件

    private List<FileVo> affixfileList;//是否已同步


    public String getAffixfile() {
        return affixfile;
    }

    public void setAffixfile(String affixfile) {
        this.affixfile = affixfile;
    }

    public List<FileVo> getAffixfileList() {
        return affixfileList;
    }

    public void setAffixfileList(List<FileVo> affixfileList) {
        this.affixfileList = affixfileList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getClicktimes() {
        return clicktimes;
    }

    public void setClicktimes(Integer clicktimes) {
        this.clicktimes = clicktimes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "HrRecruitArticle{" +
                "id=" + id +
                ", orderno=" + orderno +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dept='" + dept + '\'' +
                ", source='" + source + '\'' +
                ", publishtime=" + publishtime +
                ", author='" + author + '\'' +
                ", clicktimes=" + clicktimes +
                ", status=" + status +
                ", submitman='" + submitman + '\'' +
                ", submittime=" + submittime +
                ", modifytime=" + modifytime +
                ", content='" + content + '\'' +
                '}';
    }
}