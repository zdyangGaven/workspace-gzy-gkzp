package com.nsoft.gkzp.plan.entity;

import javax.persistence.Id;
import java.io.Serializable;

public class Uenumdata implements Serializable {
    @Id
    private Integer id;

    private String enumtypecode;//类别

    private Integer enumvalue;//key

    private String enumstring;//value

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnumtypecode() {
        return enumtypecode;
    }

    public void setEnumtypecode(String enumtypecode) {
        this.enumtypecode = enumtypecode == null ? null : enumtypecode.trim();
    }

    public Integer getEnumvalue() {
        return enumvalue;
    }

    public void setEnumvalue(Integer enumvalue) {
        this.enumvalue = enumvalue;
    }

    public String getEnumstring() {
        return enumstring;
    }

    public void setEnumstring(String enumstring) {
        this.enumstring = enumstring == null ? null : enumstring.trim();
    }
}