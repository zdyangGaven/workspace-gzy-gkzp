package com.nsoft.gkzp.plan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Integer planid;//所属招聘计划

    private Integer postid;//岗位

    private Integer posttypeid;//岗位类别

    private Integer headimage;//头像

    private String name;//姓名

    private Integer gender;//性别

    private String idcardno;//身份证号

    private Integer nation;//民族

    private Integer politics;//政治面貌

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthdate;//出生年月

    //不映射数据库字段
    @Transient
    private String birthdateStr;

    private Integer maritalstatus;//婚姻情况

    private String nativeplace;//籍贯

    private String faith;//宗教信仰

    private String height;//身高

    private String fulltimeschooling;//全日制教育

    private String degrees1;//学位(全日制教育)

    private String inserviceedu;//在职教育

    private String degrees2;//学位(在职教育)

    private String speciality;//特长

    private Integer workingyears;//年限(工龄)

    private String certifiedinfo1;//专业资格信息

    private String certifiedinfo2;//职业资格信息

    private Integer isobey;//是否服从调剂

    private Integer isinternalstaff;//是否编内人员

    private Integer isnewest;//是否最新信息

    private String submitman;//提交人

    @JsonIgnore
    private Date submittime;//提交时间

    @JsonIgnore
    private Date modifytime;//修改时间

    private Integer loginuserid;//所处审查环节

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date signuptime;//报名时间

    private Integer syncstatus;//是否已同步

    private Integer finalresult;//最终审核结果

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

    public Integer getFinalresult() {
        return finalresult;
    }

    public void setFinalresult(Integer finalresult) {
        this.finalresult = finalresult;
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