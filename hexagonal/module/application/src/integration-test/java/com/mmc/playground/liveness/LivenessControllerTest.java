package com.mmc.playground.liveness;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {TestApplication.class})
@TestPropertySource(value = "classpath:application-test.properties")
public class LivenessControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void healthCheckEndpointShouldWorks() {
        LivenessState result = restTemplate.getForObject("http://localhost:" + port + "/healthcheck", LivenessState.class);

        assertEquals(result.getStatus(), "OK");
    }
}