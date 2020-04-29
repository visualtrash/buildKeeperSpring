package com.nikulin.buildKeeper;

import com.nikulin.buildKeeper.configs.TestJpaConfig;
import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.services.ItemService;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestJpaConfig.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext
public class TestItemService {

    @Autowired
    private ItemService itemService;

    @Test
    public void tryAddItem() {
        try {
            Item item = itemService.add("itemName");

            if (!itemService.getItemById(item.getId()).isPresent()) {
                Assertions.fail();
            }

        } catch (Exception e) {
            Assertions.fail(e.toString());
        }
    }

    @Test
    public void tryAddSameNameItem() {
        try {
            itemService.add("itemName");
            itemService.add("itemName");
            Assertions.fail();

        } catch (DataIntegrityViolationException e) {
            System.out.println(e);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }


    @Test
    public void tryRemoveItem() {
        try {
            Item item = itemService.add("itemName");

            itemService.removeById(item.getId());

            if (itemService.getItemById(item.getId()).isPresent()) {
                Assertions.fail();
            }
        } catch (Exception e) {
            Assertions.fail(e.toString());
        }
    }

    @Test
    public void tryRemoveNotExistItem() {
        try {
            itemService.removeById(111);
            Assertions.fail();
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
        }
    }

    @After
    public void afterEach() {
        itemService.deleteAll();
    }
}
