package com.nikulin.buildKeeper.dal.repositories;

import com.nikulin.buildKeeper.dal.entities.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HeroRepository extends CrudRepository<Hero, Integer> {

    Long deleteByName(String name);
}
