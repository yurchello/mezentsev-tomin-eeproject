package com.mezentsev_tomin.adminpanel.forms;

/**
 * Created by Yuriy on 05.06.2016.
 */
public class LoginForm extends AbstractLoginForm {

    private static final long serialVersionUID = 3481761127278255990L;
    private int idRole;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
