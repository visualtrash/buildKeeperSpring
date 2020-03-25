package com.nikulin.buildKeeper.services;

import com.nikulin.buildKeeper.dal.entities.Build;
import com.nikulin.buildKeeper.dal.entities.Hero;
import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.dal.repositories.BuildRepository;
import com.nikulin.buildKeeper.enums.Ability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class BuildService {

    @Autowired
    private BuildRepository buildRepository;

    public Iterable<Build> getAll() {
        return buildRepository.findAll();
    }


    public Build add(Build build) {
        return buildRepository.save(build);
    }

//    public Build add(String name, Enum position, Hero hero,
//                     Iterable<Item> items, Iterable<Ability> abilities) {
//
//
//        return buildRepository.save(new Build(name, position, hero, items, abilities));
//    }

    public void removeById(Integer id) {
        buildRepository.deleteById(id);
    }

    public void remove(Build build) {
        buildRepository.delete(build);
    }

//    TODO
//    public void updateBuild()
}
