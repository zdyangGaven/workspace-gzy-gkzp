package com.nsoft.gkzp.common.entity;

public class FileVo {
    private int id;


    private String name;

    private String url;

    @Override
    public String toString() {
        return "FileVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
