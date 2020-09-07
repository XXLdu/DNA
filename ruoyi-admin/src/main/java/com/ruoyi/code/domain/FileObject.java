package com.ruoyi.code.domain;

import sun.net.www.content.image.gif;
import sun.net.www.content.image.png;

public class FileObject {
    private String kid;
    private String type;
    private String caption;
    private int size = 1;
    private String url ; //删除请求地址

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if("txt".indexOf(type)>-1){
            type = "text";
        }else if("doc,docx,xls,xlsx".indexOf(type)>-1){
            type = "office";
        }else if("jpg,png,gif".indexOf(type)>-1){
            type = "image";
        }else{
            type = "";
        }
            this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
