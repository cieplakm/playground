package com.mmc.playground.surname;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RestSurnameService implements SurnameService {

    private final WebClient webClient;

    @Override
    public String fetchById(String id) {
        WebClient.UriSpec<?> requestHeadersUriSpec = webClient.get();

        WebClient.RequestHeadersSpec<?> uri = requestHeadersUriSpec.uri("http://localhost:8080/surname");

        return uri.exchangeToMono(clientResponse -> clientResponse.bodyToMono(SurnameResponse.class))
                .block()
                .getSurname();
    }
}
