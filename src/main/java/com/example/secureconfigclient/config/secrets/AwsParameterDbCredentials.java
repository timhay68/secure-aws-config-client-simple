package com.example.secureconfigclient.config.secrets;

import au.com.haystacker.secureawsconfig.parameters.annotation.AwsParameter;
import au.com.haystacker.secureawsconfig.parameters.config.EnableSecureAWSParameters;
import com.example.secureconfigclient.config.DbCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"awsParameterConfig"})
@Configuration
@EnableSecureAWSParameters
public class AwsParameterDbCredentials {

    private static final Logger LOG = LoggerFactory.getLogger(AwsParameterDbCredentials.class);

    @AwsParameter(name = "/db/username")
    private String username;

    @AwsParameter(name = "/db/password")
    private String password;

    public AwsParameterDbCredentials() {
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
