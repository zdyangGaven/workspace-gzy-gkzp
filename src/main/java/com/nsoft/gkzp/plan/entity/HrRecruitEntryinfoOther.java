package com.nsoft.gkzp.plan.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HrRecruitEntryinfoOther implements Serializable {
    @Id
    @Column(insertable=false)
    private Integer id;

    private Integer baseid;

    private String telephone;

    private String email;

    private String address;

    private String achievementsandjustice;

    private String avoidinfo;

    private String unitaddress1;

    private String contactsinfo1;

    private String unitaddress2;

    private String contactsinfo2;

    private Integer syncstatus;

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