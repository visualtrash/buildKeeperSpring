package com.nikulin.buildKeeper;

import com.nikulin.buildKeeper.configs.JpaConfig;
import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.services.ItemService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext
public class TestItemService {

    @Autowired
    private ItemService itemService;

    @Test
    public void testAdd() {
        try {
            Item item = itemService.add("itemName");

            if (!itemService.getItemById(item.getId()).isPresent()) {
                Assertions.fail();
            }

        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void testNotExistItemAdd() {
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


//    @Test
//    public void removeById() {
//        try {
///           Integer
///           itemRepository.deleteById(());
//        } catch (Exception e) {
//            Assertions.fail();
//        }
//    }
}
