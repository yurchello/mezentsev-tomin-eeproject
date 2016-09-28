package com.airplaneSoft.translateMeDude.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Mezentsev.Y on 8/21/2016.
 */
public class JsonLogo implements Serializable {
    @JsonProperty
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
