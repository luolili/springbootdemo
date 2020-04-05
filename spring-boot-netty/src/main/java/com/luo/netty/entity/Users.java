package com.luo.netty.entity;

import java.io.Serializable;

/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2020-04-05 11:33:42
 */
public class Users implements Serializable {
    private static final long serialVersionUID = -79170153153449031L;

    private String id;

    private String username;

    private String password;

    private String faceImg;

    private String faceImgBig;

    private String qrcode;
    /**
     * client id
     */
    private Integer cid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getFaceImgBig() {
        return faceImgBig;
    }

    public void setFaceImgBig(String faceImgBig) {
        this.faceImgBig = faceImgBig;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

}