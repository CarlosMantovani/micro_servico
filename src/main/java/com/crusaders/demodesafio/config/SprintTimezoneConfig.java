package com.crusaders.demodesafio.config;

import jakarta.annotation.PostConstruct;

import java.util.TimeZone;

public class SprintTimezoneConfig {

    @PostConstruct
    public void timezoneConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
}
