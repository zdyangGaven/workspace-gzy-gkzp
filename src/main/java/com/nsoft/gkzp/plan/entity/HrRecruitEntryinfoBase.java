package com.nsoft.gkzp.plan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

public class HrRecruitEntryinfoBase implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(insertable=false)
    private Integer id;

    private Integer planid;

    private Integer postid;

    private Integer posttypeid;

    private Integer headimage;

    private String name;

    private Integer gender;

    private String idcardno;

    private Integer nation;

    private Integer politics;

    private Date birthdate;

    //不映射数据库字段
    @Transient
    private String birthdateStr;

    private Integer maritalstatus;

    private String nativeplace;

    private String faith;

    private String height;

    private String fulltimeschooling;

    private String degrees1;

    private String inserviceedu;

    private String degrees2;

    private String speciality;

    private Integer workingyears;

    private String certifiedinfo1;

    private String certifiedinfo2;

    private Integer isobey;

    private Integer isinternalstaff;

    private Integer isnewest;

    private String submitman;

    @JsonIgnore
    private Date submittime;

    @JsonIgnore
    private Date modifytime;

    private Integer loginuserid;

    private Date signuptime;

    private Integer syncstatus;

    @Override
    public String toString() {
        return "HrRecruitEntryinfoBase{" +
                "id=" + id +
                ", planid=" + planid +
                ", postid=" + postid +
                ", posttypeid=" + posttypeid +
                ", headimage=" + headimage +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", idcardno='" + idcardno + '\'' +
                ", nation=" + nation +
                ", politics=" + politics +
                ", birthdate='" + birthdate + '\'' +
                ", maritalstatus=" + maritalstatus +
                ", nativeplace='" + nativeplace + '\'' +
                ", faith='" + faith + '\'' +
                ", height='" + height + '\'' +
                ", fulltimeschooling='" + fulltimeschooling + '\'' +
                ", degrees1='" + degrees1 + '\'' +
                ", inserviceedu='" + inserviceedu + '\'' +
                ", degrees2='" + degrees2 + '\'' +
                ", speciality='" + speciality + '\'' +
                ", workingyears=" + workingyears +
                ", certifiedinfo1='" + certifiedinfo1 + '\'' +
                ", certifiedinfo2='" + certifiedinfo2 + '\'' +
                ", isobey=" + isobey +
                ", isinternalstaff=" + isinternalstaff +
                ", isnewest=" + isnewest +
                ", submitman='" + submitman + '\'' +
                ", submittime=" + submittime +
                ", modifytime=" + modifytime +
                ", loginuserid=" + loginuserid +
                '}';
    }

    public String getBirthdateStr() {
        return birthdateStr;
    }

    public void setBirthdateStr(String birthdateStr) {
        this.birthdateStr = birthdateStr;
    }

    public Integer getSyncstatus() {
        return syncstatus;
    }

    public void setSyncstatus(Integer syncstatus) {
        this.syncstatus = syncstatus;
    }

    public Date getSignuptime() {
        return signuptime;
    }

    public void setSignuptime(Date signuptime) {
        this.signuptime = signuptime;
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

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public Integer getPosttypeid() {
        return posttypeid;
    }

    public void setPosttypeid(Integer posttypeid) {
        this.posttypeid = posttypeid;
    }

    public Integer getHeadimage() {
        return headimage;
    }

    public void setHeadimage(Integer headimage) {
        this.headimage = headimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno == null ? null : idcardno.trim();
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public Integer getPolitics() {
        return politics;
    }

    public void setPolitics(Integer politics) {
        this.politics = politics;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(Integer maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace == null ? null : nativeplace.trim();
    }

    public String getFaith() {
        return faith;
    }

    public void setFaith(String faith) {
        this.faith = faith == null ? null : faith.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getFulltimeschooling() {
        return fulltimeschooling;
    }

    public void setFulltimeschooling(String fulltimeschooling) {
        this.fulltimeschooling = fulltimeschooling == null ? null : fulltimeschooling.trim();
    }

    public String getDegrees1() {
        return degrees1;
    }

    public void setDegrees1(String degrees1) {
        this.degrees1 = degrees1 == null ? null : degrees1.trim();
    }

    public String getInserviceedu() {
        return inserviceedu;
    }

    public void setInserviceedu(String inserviceedu) {
        this.inserviceedu = inserviceedu == null ? null : inserviceedu.trim();
    }

    public String getDegrees2() {
        return degrees2;
    }

    public void setDegrees2(String degrees2) {
        this.degrees2 = degrees2 == null ? null : degrees2.trim();
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality == null ? null : speciality.trim();
    }

    public Integer getWorkingyears() {
        return workingyears;
    }

    public void setWorkingyears(Integer workingyears) {
        this.workingyears = workingyears;
    }

    public String getCertifiedinfo1() {
        return certifiedinfo1;
    }

    public void setCertifiedinfo1(String certifiedinfo1) {
        this.certifiedinfo1 = certifiedinfo1 == null ? null : certifiedinfo1.trim();
    }

    public String getCertifiedinfo2() {
        return certifiedinfo2;
    }

    public void setCertifiedinfo2(String certifiedinfo2) {
        this.certifiedinfo2 = certifiedinfo2 == null ? null : certifiedinfo2.trim();
    }

    public Integer getIsobey() {
        return isobey;
    }

    public void setIsobey(Integer isobey) {
        this.isobey = isobey;
    }

    public Integer getIsinternalstaff() {
        return isinternalstaff;
    }

    public void setIsinternalstaff(Integer isinternalstaff) {
        this.isinternalstaff = isinternalstaff;
    }

    public Integer getIsnewest() {
        return isnewest;
    }

    public void setIsnewest(Integer isnewest) {
        this.isnewest = isnewest;
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

    public Integer getLoginuserid() {
        return loginuserid;
    }

    public void setLoginuserid(Integer loginuserid) {
        this.loginuserid = loginuserid;
    }
}