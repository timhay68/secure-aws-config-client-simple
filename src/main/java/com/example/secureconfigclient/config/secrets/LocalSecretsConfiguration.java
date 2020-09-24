package com.example.secureconfigclient.config.secrets;

import com.example.secureconfigclient.config.DbCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"!awsParameterConfig & !awsSecretConfig"})
@Component
@ConfigurationProperties("spring.datasource")
public class LocalSecretsConfiguration {

    private String username;
    private String password;

    @Bean
    public DbCredentials dbCredentials() {
        return new DbCredentials(username, password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
