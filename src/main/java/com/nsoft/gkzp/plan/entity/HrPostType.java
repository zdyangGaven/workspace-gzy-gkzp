package com.nsoft.gkzp.plan.entity;

public class HrPostType {
    private Integer id;

    private String typename;

    private Integer typeorder;

    private String typeremark;

    private String typeparent;

    private String typestring;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Integer getTypeorder() {
        return typeorder;
    }

    public void setTypeorder(Integer typeorder) {
        this.typeorder = typeorder;
    }

    public String getTyperemark() {
        return typeremark;
    }

    public void setTyperemark(String typeremark) {
        this.typeremark = typeremark == null ? null : typeremark.trim();
    }

    public String getTypeparent() {
        return typeparent;
    }

    public void setTypeparent(String typeparent) {
        this.typeparent = typeparent == null ? null : typeparent.trim();
    }

    public String getTypestring() {
        return typestring;
    }

    public void setTypestring(String typestring) {
        this.typestring = typestring == null ? null : typestring.trim();
    }
}