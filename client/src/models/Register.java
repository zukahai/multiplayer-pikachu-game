package models;

import java.io.Serializable;

public class Register implements Serializable{
    private User user;

    public Register() {
        this.user = null;
    }
    
    public Register(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
