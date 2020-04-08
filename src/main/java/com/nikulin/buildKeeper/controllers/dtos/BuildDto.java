package com.nikulin.buildKeeper.controllers.dtos;

import com.nikulin.buildKeeper.enums.HeroPosition;
import lombok.Data;

import java.util.List;

@Data
public class BuildDto extends BaseDto {
    String name;
    HeroPosition heroPosition;
    BaseDto hero;
    String abilities;
    List<BaseDto> items;
    String runeSet1;
    String runeSet2;
    String runeSet3;
}
