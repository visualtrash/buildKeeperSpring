package com.nikulin.buildKeeper.dal.repositories;

import com.nikulin.buildKeeper.dal.entities.Build;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildRepository extends CrudRepository<Build, Integer> {
    Long deleteByName(String name);
}