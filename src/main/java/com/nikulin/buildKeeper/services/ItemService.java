package com.nikulin.buildKeeper.services;

import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.dal.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item add(String itemName) {
        return itemRepository.save(new Item(itemName));
    }

    public Iterable<Item> getAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Integer itemId) {
        return itemRepository.findById(itemId);
    }

    public void removeById(Integer itemId) {
        itemRepository.deleteById(itemId);
    }

    public void deleteAll() {
        itemRepository.deleteAll();
    }

    @Transactional
    public void removeByName(String name) {
        itemRepository.deleteByName(name);
    }
}