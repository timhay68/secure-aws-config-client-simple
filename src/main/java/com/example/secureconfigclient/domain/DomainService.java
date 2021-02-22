package com.example.secureconfigclient.domain;

import com.example.secureconfigclient.domain.health.ServiceHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.DependsOn;
import com.example.secureconfigclient.config.DbCredentials;

import java.util.Arrays;

@Service
@DependsOn(value = {"awsSecretsCredentials", "awsParameterCredentials"})
public class DomainService {

    private static final String CREDENTIALS_TEMPLATE = "Your AWS %s credentials: %s   |   %s";

    @Autowired
    @Qualifier("awsSecretsCredentials")
    private DbCredentials awsSecretsCredentials;

    @Autowired
    @Qualifier("awsParameterCredentials")
    private DbCredentials awsParameterCredentials;

    public DomainResponse serve() {
        final String awsSecretsCredentialsResponse = String.format(CREDENTIALS_TEMPLATE,
                "Secrets Manager",
                awsSecretsCredentials.getUsername(),
                awsSecretsCredentials.getPassword());

        final String awsParameterCredentialsResponse = String.format(CREDENTIALS_TEMPLATE,
                "Parameter Store",
                awsParameterCredentials.getUsername(),
                awsParameterCredentials.getPassword());

        return DomainResponse.of(Arrays.asList(awsSecretsCredentialsResponse, awsParameterCredentialsResponse), "OK");
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
