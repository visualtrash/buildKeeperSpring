package com.nikulin.buildKeeper.controllers;

import com.nikulin.buildKeeper.dal.entities.Hero;
import com.nikulin.buildKeeper.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/heroes")
public class HeroController {

    @Autowired
    HeroService heroService;

    @RequestMapping(method = RequestMethod.POST, path = "create")
    public Hero create(@RequestBody String name) {
        return heroService.add(name);
    }

    @RequestMapping(method = RequestMethod.POST, path = "deleteByName")
    public void deleteByName(@RequestBody String name) {
        heroService.removeByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, path = "getAll")
    public Iterable<Hero> getAll() {
        return heroService.getAll();
    }
}
