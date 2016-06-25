package com.mezentsev_tomin.adminpanel.forms;

/**
 * Created by Yuriy on 05.06.2016.
 */
public class AbstractLoginForm implements IForm{

    private static final long serialVersionUID = -8317239495588868934L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
