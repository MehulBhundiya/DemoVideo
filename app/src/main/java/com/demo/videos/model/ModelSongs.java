package com.demo.videos.model;

import java.io.Serializable;

public class ModelSongs implements Serializable {
    public String id;
    public String name;
    public String nameFull;
    public int songTypeId;
    public String subText;
    public String subTextFullJsonArray;
    public String thumbMedium;
    public String thumbSmall;
    public int viewType;
    public String youtubeUrl;

    public ModelSongs(int i) {
        this.viewType = i;
    }

    public String toString() {
        return "ModelSongs{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", thumbSmall='" + this.thumbSmall
                + '\'' + ", thumbMedium='" + this.thumbMedium + '\'' + ", subText='" + this.subText + '\''
                + ", youtubeUrl='" + this.youtubeUrl + '\'' + ", songTypeId=" + this.songTypeId + ", nameFull='"
                + this.nameFull + '\'' + ", subTextFullJsonArray='" + this.subTextFullJsonArray + '\'' + ", viewType="
                + this.viewType + '}';
    }
}
