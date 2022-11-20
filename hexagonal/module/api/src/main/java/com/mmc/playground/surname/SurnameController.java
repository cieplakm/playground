package com.mmc.playground.surname;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surname")
@RequiredArgsConstructor
public class SurnameController {

    private final SurnameService surnameService;

    @GetMapping
    String get() {
        return surnameService.fetchById("ud");
    }
}
