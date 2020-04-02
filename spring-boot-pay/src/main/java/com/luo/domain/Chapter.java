package com.luo.domain;


import java.io.Serializable;

public class Chapter implements Serializable {

    private Integer id;
    private Integer videoId;
    private String title;
    private Integer orderd;
    private java.util.Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getOrderd() {
        return orderd;
    }

    public void setOrderd(Integer orderd) {
        this.orderd = orderd;
    }


    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

}
