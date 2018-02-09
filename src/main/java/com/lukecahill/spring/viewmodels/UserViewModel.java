package com.lukecahill.spring.viewmodels;

import com.lukecahill.spring.models.User;

public class UserViewModel {

    private String username;
    private String name;
    private String email;
    private boolean enabled;

    public UserViewModel() {}

    public UserViewModel(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
