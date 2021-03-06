package com.nikulin.buildKeeper.controllers;

import com.nikulin.buildKeeper.controllers.dtos.BuildDto;
import com.nikulin.buildKeeper.dal.entities.Build;
import com.nikulin.buildKeeper.dal.entities.Hero;
import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.enums.Ability;
import com.nikulin.buildKeeper.enums.HeroPosition;
import com.nikulin.buildKeeper.services.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/builds")
public class BuildController {

    @Autowired
    BuildService buildService;



    @RequestMapping(method = RequestMethod.POST, path = "create")
    public Build create(@RequestBody BuildDto build) {
        return buildService.add(build);
    }

    @RequestMapping(method = RequestMethod.GET, path = "getAll")
    public Iterable<Build> getAll() {
        return buildService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "deleteById")
    public void deleteById(@RequestBody Integer id) {
        buildService.removeById(id);
    }


    @RequestMapping(method = RequestMethod.GET, path = "getById")
    public Optional<Build> getById(@RequestParam Integer id) {
        return buildService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "update")
    public void updateById(@RequestBody BuildDto build) {
        buildService.updateBuild(build);
    }
}
