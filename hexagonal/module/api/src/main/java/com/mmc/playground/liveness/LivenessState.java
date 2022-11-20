package com.mmc.playground.liveness;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
class LivenessState {

    String status;
}
