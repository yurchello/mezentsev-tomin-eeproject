package com.airplaneSoft.translateMeDude.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mezentsev.Y on 8/11/2016.
 */
public class MultiFileBucket {
    List<FileBucket> files = new ArrayList<FileBucket>();

    public MultiFileBucket(){
        files.add(new FileBucket());
        files.add(new FileBucket());
        files.add(new FileBucket());
    }

    public List<FileBucket> getFiles() {
        return files;
    }

    public void setFiles(List<FileBucket> files) {
        this.files = files;
    }
}
