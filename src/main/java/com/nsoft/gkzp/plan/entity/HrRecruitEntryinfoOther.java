package com.nsoft.gkzp.plan.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HrRecruitEntryinfoOther implements Serializable {
    @Id
    @Column(insertable=false)
    private Integer id;

    private Integer baseid;//基本信息ID

    private String telephone;//联系电话

    private String email;//电子邮箱

    private String address;//现居住地址

    private String achievementsandjustice;//科研成果及奖惩情况

    private String avoidinfo;//需回避的情况

    private String unitaddress1;//人事档案保存单位及地址

    private String contactsinfo1;//联系人及联系电话

    private String unitaddress2;//党员档案保存单位及地址

    private String contactsinfo2;//联系人及联系电话

    private Integer syncstatus;//是否已同步

    public Integer getSyncstatus() {
        return syncstatus;
    }

    public void setSyncstatus(Integer syncstatus) {
        this.syncstatus = syncstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseid() {
        return baseid;
    }

    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAchievementsandjustice() {
        return achievementsandjustice;
    }

    public void setAchievementsandjustice(String achievementsandjustice) {
        this.achievementsandjustice = achievementsandjustice == null ? null : achievementsandjustice.trim();
    }

    public String getAvoidinfo() {
        return avoidinfo;
    }

    public void setAvoidinfo(String avoidinfo) {
        this.avoidinfo = avoidinfo == null ? null : avoidinfo.trim();
    }

    public String getUnitaddress1() {
        return unitaddress1;
    }

    public void setUnitaddress1(String unitaddress1) {
        this.unitaddress1 = unitaddress1 == null ? null : unitaddress1.trim();
    }

    public String getContactsinfo1() {
        return contactsinfo1;
    }

    public void setContactsinfo1(String contactsinfo1) {
        this.contactsinfo1 = contactsinfo1 == null ? null : contactsinfo1.trim();
    }

    public String getUnitaddress2() {
        return unitaddress2;
    }

    public void setUnitaddress2(String unitaddress2) {
        this.unitaddress2 = unitaddress2 == null ? null : unitaddress2.trim();
    }

    public String getContactsinfo2() {
        return contactsinfo2;
    }

    public void setContactsinfo2(String contactsinfo2) {
        this.contactsinfo2 = contactsinfo2 == null ? null : contactsinfo2.trim();
    }

    @Override
    public String toString() {
        return "HrRecruitEntryinfoOther{" +
                "id=" + id +
                ", baseid=" + baseid +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", achievementsandjustice='" + achievementsandjustice + '\'' +
                ", avoidinfo='" + avoidinfo + '\'' +
                ", unitaddress1='" + unitaddress1 + '\'' +
                ", contactsinfo1='" + contactsinfo1 + '\'' +
                ", unitaddress2='" + unitaddress2 + '\'' +
                ", contactsinfo2='" + contactsinfo2 + '\'' +
                '}';
    }
}