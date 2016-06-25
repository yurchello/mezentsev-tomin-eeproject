package com.mezentsev_tomin.adminpanel.forms;

/**
 * Created by Yuriy on 18.06.2016.
 */
public class UserForm extends AbstractLoginForm{

    private static final long serialVersionUID = -8353343409739766211L;


    private String email;
    private String photoPath;
    private String description;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
