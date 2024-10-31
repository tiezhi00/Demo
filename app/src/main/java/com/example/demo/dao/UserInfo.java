package com.example.demo.dao;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String username;
    private int level;
    private String avatarUrl;
    private float weight;

    public UserInfo() {
    }

    public UserInfo(String useername, int level) {
        this.username = useername;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String useername) {
        this.username = useername;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
