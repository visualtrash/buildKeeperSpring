package com.nikulin.buildKeeper.dal.repositories;

import com.nikulin.buildKeeper.dal.entities.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

}