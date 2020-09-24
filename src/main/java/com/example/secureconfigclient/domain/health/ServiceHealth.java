package com.example.secureconfigclient.domain.health;

public final class ServiceHealth {

    private String status;

    public ServiceHealth() {
    }

    public ServiceHealth(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
