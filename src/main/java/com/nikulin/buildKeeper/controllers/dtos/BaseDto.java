package com.nikulin.buildKeeper.controllers.dtos;

import lombok.Data;

@Data
public class BaseDto {
    Integer id;

    public BaseDto() {
    }

    public BaseDto(Integer id) {
        this.id = id;
    }
}
