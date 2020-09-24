package com.example.secureconfigclient.config.secrets;

import au.com.haystacker.secureawsconfig.secrets.annotation.AwsSecret;
import au.com.haystacker.secureawsconfig.secrets.config.EnableSecureAWSSecrets;
import com.example.secureconfigclient.config.DbCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"awsSecretConfig"})
@Configuration
@EnableSecureAWSSecrets
public class AwsSecretDbCredentials {

    private static final Logger LOG = LoggerFactory.getLogger(AwsSecretDbCredentials.class);

    @AwsSecret(secretKey = "mysql-username")
    private String username;

    @AwsSecret(secretKey = "mysql-password")
    private String password;

    public AwsSecretDbCredentials() {
    }

    @Bean
    public DbCredentials dbCredentials() {
        return new DbCredentials(username, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
