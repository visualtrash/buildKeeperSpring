package com.nikulin.buildKeeper.controllers;

import com.nikulin.buildKeeper.dal.entities.Hero;
import com.nikulin.buildKeeper.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @RequestMapping(method = RequestMethod.POST, path = "deleteById")
    public void deleteById(@RequestBody Integer id) {
        heroService.removeById(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "getAll")
    public Iterable<Hero> getAll() {
        return heroService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "getByName")
    public Optional<Hero> getHero(@RequestBody String name) {
        return heroService.getHeroByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, path = "getById")
    public Optional<Hero> getHero(@RequestBody Integer id) {
        return heroService.getHeroById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "editName")
    public Hero editName(@RequestParam(value="id") Integer id, @RequestParam(value="newName")String newName) throws Exception {
        return  heroService.editHeroName(id, newName);

    }
}
