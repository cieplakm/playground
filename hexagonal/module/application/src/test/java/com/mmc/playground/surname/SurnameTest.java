package com.mmc.playground.surname;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;


@SpringBootTest
@AutoConfigureMockMvc
//@WireMockTest(httpPort = 8080)
public class SurnameTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void should() {
//        stubFor(WireMock.get(urlEqualTo("/healthcheck"))
//                .willReturn(okJson("{\"status\" : \"OK\"}")));

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/healthcheck"));

        actions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("OK")));
    }
}
