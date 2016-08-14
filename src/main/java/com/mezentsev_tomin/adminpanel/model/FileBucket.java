package com.mezentsev_tomin.adminpanel.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
