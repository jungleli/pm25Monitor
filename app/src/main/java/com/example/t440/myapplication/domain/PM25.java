package com.example.t440.myapplication.domain;

import com.google.gson.annotations.SerializedName;

public class PM25 {
    @SerializedName("position_name")
    private String positionName;

    @SerializedName("quality")
    private String quality;

    @SerializedName("pm2_5")
    private Long pm25Value;


    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getQuality() {
        return quality;
    }

    public Long getpm25Value() {
        return pm25Value;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
