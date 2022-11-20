package com.mmc.playground.surname;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class SurnameResponse {

    String surname;
}
