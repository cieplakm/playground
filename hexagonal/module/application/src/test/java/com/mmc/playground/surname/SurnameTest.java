//package com.mmc.playground.surname;
//
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.junit5.WireMockTest;
//import com.mmc.playground.Application;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
//import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
//import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@ContextConfiguration(classes = {Application.class})
//@AutoConfigureMockMvc
//@WireMockTest(httpPort = 8080)
//public class SurnameTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @SneakyThrows
//    public void should() {
//
//        stubFor(WireMock.get(urlEqualTo("/surname"))
//                .willReturn(okJson("{\"surname\" : \"Cieplak\"}")));
//
//        ResultActions actions = mockMvc.perform(get("/surname"));
//
//        actions.andExpect(status().isOk())
//                .andExpect(content().string(containsString("Cieplak")));
//    }
//}
