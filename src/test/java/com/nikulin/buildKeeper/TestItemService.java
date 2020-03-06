package com.nikulin.buildKeeper;

import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.dal.repositories.ItemRepository;
import com.nikulin.buildKeeper.services.ItemService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import java.util.UUID;

public class TestItemService {
    private ItemService itemService = new ItemService(new ItemRepository());
    private ItemRepository itemRepository = new ItemRepository();

    private UUID getRandomIdItem() {
        List<Item> itemList = itemRepository.getAll();
        return itemList.get(0).getId();
    }

    @Test
    public void testAdd() {
        try {
            itemService.add("itemName");
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void removeById() {
        try {
            itemRepository.deleteById(getRandomIdItem());
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
