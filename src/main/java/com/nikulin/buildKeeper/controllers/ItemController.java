package com.nikulin.buildKeeper.controllers;

import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/items")
public class ItemController {
    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET, path = "getAll")
    public Iterable<Item> getAll() {
        return itemService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "create")
    public Item create(@RequestBody String name) {
        return itemService.add(name);
    }
}
