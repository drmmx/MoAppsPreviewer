package com.drmmx.devmax.moappspreviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInData {

    @SerializedName("userNick")
    @Expose
    private String userNick;
    @SerializedName("password")
    @Expose
    private String password;

    public SignInData(String userNick, String password) {
        this.userNick = userNick;
        this.password = password;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
