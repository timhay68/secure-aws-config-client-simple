package com.example.secureconfigclient.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

@Profile("local")
@Configuration
public class AWSCredentialsConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(AWSCredentialsConfiguration.class);

    @Value("${secure-aws-config.awsProfile}")
    private String awsProfile;

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {

        LOG.debug("Using AWS profile " + awsProfile);

        final ProfileCredentialsProvider profileCredentialsProvider = ProfileCredentialsProvider.create(awsProfile);
        final AwsCredentials credentials = profileCredentialsProvider.resolveCredentials();

        return StaticCredentialsProvider.create(credentials);
    }
}
