package com.example.secureconfigclient.domain;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public final class DomainHealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        return null;
    }
}
