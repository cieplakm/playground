package com.mmc.playground.liveness;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
class LivenessState {

    String appName;
    String status;
    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();
}
