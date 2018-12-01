
package com.drmmx.devmax.moappspreviewer.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreviewResponse {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("err")
    @Expose
    private Boolean err;
    @SerializedName("code")
    @Expose
    private Integer code;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Boolean getErr() {
        return err;
    }

    public void setErr(Boolean err) {
        this.err = err;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
