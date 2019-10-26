package com.nsoft.gkzp.common.entity;

public class AffixfileWithBLOBs extends Affixfile {
    private String fileinfo;

    private String usernote;

    public String getFileinfo() {
        return fileinfo;
    }

    public void setFileinfo(String fileinfo) {
        this.fileinfo = fileinfo == null ? null : fileinfo.trim();
    }

    public String getUsernote() {
        return usernote;
    }

    public void setUsernote(String usernote) {
        this.usernote = usernote == null ? null : usernote.trim();
    }
}