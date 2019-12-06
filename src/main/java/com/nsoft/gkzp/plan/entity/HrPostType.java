package com.nsoft.gkzp.plan.entity;

public class HrPostType {
    private Integer id;

    private String typename;//类别名称

    private Integer typeorder;//排序

    private String typeremark;//奋注

    private Integer typeparent;//上级ID

    private String typestring;//类别标识

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

    public Integer getTypeparent() {
        return typeparent;
    }

    public void setTypeparent(Integer typeparent) {
        this.typeparent = typeparent;
    }

    public String getTypestring() {
        return typestring;
    }

    public void setTypestring(String typestring) {
        this.typestring = typestring == null ? null : typestring.trim();
    }
}