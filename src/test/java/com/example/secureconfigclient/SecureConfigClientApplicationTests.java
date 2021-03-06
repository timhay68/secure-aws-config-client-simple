package com.example.secureconfigclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestClientConfiguration.class)
class SecureConfigClientApplicationTests {

    @Value("${spring.application.name}")
    private String clonedName;

    @Test
    public void contextLoads() {
        assertThat(clonedName).isEqualTo("AWS Secret Parameters Annotations Test App");
    }

}
