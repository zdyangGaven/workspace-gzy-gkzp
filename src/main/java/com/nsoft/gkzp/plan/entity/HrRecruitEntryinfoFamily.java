package com.nsoft.gkzp.plan.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HrRecruitEntryinfoFamily implements Serializable {

    @Id
    @Column(insertable=false)
    private Integer id;

    private Integer baseid;

    private String appellation;

    private String name;

    private String age;

    private Integer politics;

    private String unitsandpost;

    private String telephone;

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

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation == null ? null : appellation.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public Integer getPolitics() {
        return politics;
    }

    public void setPolitics(Integer politics) {
        this.politics = politics;
    }

    public String getUnitsandpost() {
        return unitsandpost;
    }

    public void setUnitsandpost(String unitsandpost) {
        this.unitsandpost = unitsandpost == null ? null : unitsandpost.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    @Override
    public String toString() {
        return "HrRecruitEntryinfoFamily{" +
                "id=" + id +
                ", baseid=" + baseid +
                ", appellation='" + appellation + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", politics=" + politics +
                ", unitsandpost='" + unitsandpost + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}