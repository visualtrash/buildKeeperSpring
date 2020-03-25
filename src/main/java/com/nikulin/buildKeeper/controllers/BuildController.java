package com.nikulin.buildKeeper.controllers;

import com.nikulin.buildKeeper.dal.entities.Build;
import com.nikulin.buildKeeper.dal.entities.Hero;
import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.enums.Ability;
import com.nikulin.buildKeeper.services.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/builds")
public class BuildController {

    @Autowired
    BuildService buildService;

//    @RequestMapping(method = RequestMethod.POST, path = "create")
//    public Build create(@RequestParam String name, @RequestParam Enum position, @RequestParam Hero hero,
//                        @RequestParam Iterable<Item> items, Iterable<Ability> abilities) {
//
//        return buildService.add();
//    }

    @RequestMapping(method = RequestMethod.GET, path = "getAll")
    public Iterable<Build> getAll() {
        return buildService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "deleteById")
    public void deleteById(@RequestBody Integer id) {
        buildService.removeById(id);
    }


}