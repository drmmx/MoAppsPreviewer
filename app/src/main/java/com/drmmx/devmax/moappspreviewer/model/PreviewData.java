package com.drmmx.devmax.moappspreviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreviewData {

    @SerializedName("skip")
    @Expose
    private int skip;
    @SerializedName("take")
    @Expose
    private int take;
    @SerializedName("osType")
    @Expose
    private int osType;
    @SerializedName("userToken")
    @Expose
    private String userToken;

    public PreviewData(int skip, int take, int osType, String userToken) {
        this.skip = skip;
        this.take = take;
        this.osType = osType;
        this.userToken = userToken;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

}
