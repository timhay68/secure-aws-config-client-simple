package com.example.secureconfigclient;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.web.WebAppConfiguration;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParametersByPathRequest;
import software.amazon.awssdk.services.ssm.model.GetParametersByPathResponse;
import software.amazon.awssdk.services.ssm.model.Parameter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class TestClientConfiguration {

    static class SSMTestConfiguration {

        @Primary
        @Bean
        public SsmClient ssmClient() {
            final Parameter username = Parameter.builder()
                    .name("/sampleapi/dev/db/username")
                    .value("joebloggs")
                    .build();
            final Parameter password = Parameter.builder()
                    .name("/sampleapi/dev/db/securepassword")
                    .value("Pa55w0rd!23")
                    .build();

            final SsmClient mockClient = mock(SsmClient.class);
            when(mockClient.getParametersByPath(any(GetParametersByPathRequest.class))).thenReturn(
                    GetParametersByPathResponse.builder()
                            .parameters(username, password)
                            .build()
            );
            return mockClient;
        }

        @Primary
        @Bean
        public SecretsManagerClient secretsClient() {
            SecretsManagerClient mockClient = mock(SecretsManagerClient.class);
            when(mockClient.getSecretValue(any(GetSecretValueRequest.class))).thenReturn(
                    GetSecretValueResponse.builder()
                            .secretString("{\n" +
                                    "  \"mysql-username\": \"sample_user\",\n" +
                                    "  \"mysql-password\": \"password123\"\n" +
                                    "}")
                            .build()
            );
            return mockClient;
        }

    }
}
