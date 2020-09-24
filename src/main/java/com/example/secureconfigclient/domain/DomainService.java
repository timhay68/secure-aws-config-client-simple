package com.example.secureconfigclient.domain;

import com.example.secureconfigclient.domain.health.ServiceHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.DependsOn;
import com.example.secureconfigclient.config.DbCredentials;

@Service
@DependsOn(value = {"dbCredentials"})
public class DomainService {

    private static final String TEMPLATE = "Your secret credentials: %s   |   %s";

    @Autowired
    private DbCredentials dbCredentials;

    public DomainResponse serve() {
        final String response = String.format(TEMPLATE, dbCredentials.getUsername(), dbCredentials.getPassword());

        return DomainResponse.of(response, "OK");
    }

    /**
     * Test healthcheck.
     *
     * @return Up id OK.
     */
    public String healthCheck() {
        ServiceHealth health = new ServiceHealth("UP");
        return health.getStatus();
    }


}
