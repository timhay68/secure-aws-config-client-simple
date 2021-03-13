package com.example.secureconfigclient.domain;

import com.example.secureconfigclient.domain.health.ServiceHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.DependsOn;
import com.example.secureconfigclient.config.DbCredentials;

import java.util.Arrays;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class DomainService {

    private static final String CREDENTIALS_TEMPLATE = "Your AWS %s credentials: %s | %s";

    @Autowired(required = false)
    @Qualifier("awsSecretsCredentials")
    private DbCredentials awsSecretsCredentials;

    @Autowired(required = false)
    @Qualifier("awsParameterCredentials")
    private DbCredentials awsParameterCredentials;

    public DomainResponse serve() {
        final String awsSecretsCredentialsResponse = getCredentialsForStore("Secrets Manager", awsSecretsCredentials);
        final String awsParameterCredentialsResponse = getCredentialsForStore("Parameter Store", awsParameterCredentials);

        return DomainResponse.of(
                Arrays.asList(awsSecretsCredentialsResponse,
                    awsParameterCredentialsResponse
                ),
                "OK"
        );
    }

    private String getCredentialsForStore(String storeDescription, DbCredentials credentials) {

        final String username = credentials != null ? credentials.getUsername() : "Not Available";
        final String password = credentials != null ? credentials.getPassword() : "Not Available";

        return String.format(CREDENTIALS_TEMPLATE, storeDescription, username, password);
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
