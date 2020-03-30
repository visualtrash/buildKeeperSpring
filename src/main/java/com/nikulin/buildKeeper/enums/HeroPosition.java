package com.nikulin.buildKeeper.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum HeroPosition {
    @JsonProperty("TOP")
    TOP("TOP"),
    @JsonProperty("MIDDLE")
    MIDDLE("MIDDLE"),
    @JsonProperty("SUPPORT")
    SUPPORT("SUPPORT"),
    @JsonProperty("BOTTOM")
    BOTTOM("BOTTOM"),
    @JsonProperty("JUNGLE")
    JUNGLE("JUNGLE");

    private static Map<String, HeroPosition> FORMAT_MAP = Stream
            .of(HeroPosition.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    private final String formatted;

    HeroPosition(String formatted) {
        this.formatted = formatted;
    }

    @JsonCreator // This is the factory method and must be static
    public static HeroPosition fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }


}
