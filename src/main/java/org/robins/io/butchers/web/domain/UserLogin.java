package org.robins.io.butchers.web.domain;

/**
 * Created by jonrobins on 25/02/2014.
 */
public class UserLogin
{
    private String username;
    private String password;

    UserLogin() {
    }

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
