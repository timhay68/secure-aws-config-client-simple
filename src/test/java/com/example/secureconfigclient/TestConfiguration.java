package com.example.secureconfigclient;

import com.example.secureconfigclient.config.DbCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;

@Configuration
public class TestConfiguration {

    @Bean("awsSecretsCredentials")
    public DbCredentials awsSecretsCredentials() {
        return new DbCredentials("secrets-user", "password");
    }

    @Bean("awsParameterCredentials")
    public DbCredentials awsParameterCredentials() {
        return new DbCredentials("parameters-user", "password");
    }

    @Bean
    public AwsCredentialsProvider testAWSCredentialsProvider() {
        return AnonymousCredentialsProvider.create();
    }
}
