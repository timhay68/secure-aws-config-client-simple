package com.example.secureconfigclient.config;

public class DbCredentials {

    private final String username;
    private final String password;

    public DbCredentials(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
