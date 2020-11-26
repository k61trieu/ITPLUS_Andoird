package com.nc.chien.listviewnangcao;

public class Fruit {
    private int avatar;
    private String name;
    private String desc;

    public Fruit(int avatar, String name, String desc) {
        this.avatar = avatar;
        this.name = name;
        this.desc = desc;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
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
}
