package com.mmc.playground.liveness;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
class LivenessController {

    @GetMapping
    LivenessState state() {

        return LivenessState.builder()
                .status("OK")
                .build();
    }
}
