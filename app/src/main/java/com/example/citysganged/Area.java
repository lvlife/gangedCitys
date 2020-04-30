package com.example.citysganged;

/**
 * Created by Lyudony on 2020/4/20 19:37
 * E-Mail Address：lyudony.outlook@qq.com
 */
public class Area {

    private String areaid;
    private String name;
    private String pinyin;
    private String shortpinyin;
    private String type;
    private String parentId;

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getShortpinyin() {
        return shortpinyin;
    }

    public void setShortpinyin(String shortpinyin) {
        this.shortpinyin = shortpinyin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
