package com.example.qlvattu;

import android.graphics.Bitmap;

import java.io.Serializable;

public class VatTu implements Serializable {
    private String name;
    private String desc;
    private byte[] avatar;

    public VatTu() {
    }

    public VatTu(String name, String desc, byte[] avatar) {
        this.name = name;
        this.desc = desc;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "VatTu{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
