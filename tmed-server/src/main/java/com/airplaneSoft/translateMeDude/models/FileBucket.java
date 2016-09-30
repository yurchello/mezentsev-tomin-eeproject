package com.airplaneSoft.translateMeDude.models;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Mezentsev.Y on 8/11/2016.
 */
public class FileBucket {
    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
