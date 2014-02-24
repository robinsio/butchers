package org.robins.io.butchers.persistence.domain;

/**
 * Created by jonrobins on 24/02/2014.
 */
public class User
{
    private String username;
    private String password;

    User() {
    }

    public User(String username, String password) {
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
