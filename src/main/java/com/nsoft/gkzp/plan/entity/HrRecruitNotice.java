package com.nsoft.gkzp.plan.entity;

import com.nsoft.gkzp.common.entity.FileVo;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HrRecruitNotice implements Serializable {
    @Id
    private Integer id;

    private Integer planid;//招聘计划ID

    private Integer baseid;//接受通知人ID

    private Integer type;//通知类别

    private Integer status;//状态

    private Integer hasread;//是否已读

    private String submitman;//提交人

    private Date submittime;//提交时间

    private Date modifytime;//修改时间

    private String title;//标题

    private String content;//是否已同步

    private String affix;//附件

    private List<FileVo> affixList;

    @Override
    public String toString() {
        return "HrRecruitNotice{" +
                "id=" + id +
                ", planid=" + planid +
                ", baseid=" + baseid +
                ", type=" + type +
                ", status=" + status +
                ", hasread=" + hasread +
                ", submitman='" + submitman + '\'' +
                ", submittime=" + submittime +
                ", modifytime=" + modifytime +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public List<FileVo> getAffixList() {
        return affixList;
    }

    public void setAffixList(List<FileVo> affixList) {
        this.affixList = affixList;
    }

    public String getAffix() {
        return affix;
    }

    public void setAffix(String affix) {
        this.affix = affix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanid() {
        return planid;
    }

    public void setPlanid(Integer planid) {
        this.planid = planid;
    }

    public Integer getBaseid() {
        return baseid;
    }

    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHasread() {
        return hasread;
    }

    public void setHasread(Integer hasread) {
        this.hasread = hasread;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}