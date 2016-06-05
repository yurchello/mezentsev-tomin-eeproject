package com.mezentsev_tomin.adminpanel.forms;

/**
 * Created by Yuriy on 05.06.2016.
 */
public class AbstractLoginForm implements IForm{
    private static final long serialVersionUID = -8342766271396665602L;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
