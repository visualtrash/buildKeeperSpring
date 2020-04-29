package com.nikulin.buildKeeper;

import com.nikulin.buildKeeper.configs.TestJpaConfig;
import com.nikulin.buildKeeper.dal.entities.Hero;
import com.nikulin.buildKeeper.services.HeroService;
import org.junit.After;
import org.junit.Assert;
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

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestJpaConfig.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext
public class TestHeroService {

    @Autowired
    private HeroService heroService;

    @Test
    public void tryAddHero() {
        try {
            Hero hero = heroService.add("heroName");

            if (!heroService.getHeroById(hero.getId()).isPresent()) {
                Assertions.fail();
            }

        } catch (Exception e) {
            Assertions.fail(e.toString());
        }
    }

    @Test
    public void tryAddWithSameNameHero() {
        try {
            heroService.add("heroName");
            heroService.add("heroName");
            Assertions.fail();

        } catch (DataIntegrityViolationException e) {
            System.out.println(e);
        } catch (Exception e) {
            Assertions.fail(e.toString());
        }
    }

    @Test
    public void tryRemoveHero() {
        try {
            Hero hero = heroService.add("heroName");

            heroService.removeById(hero.getId());

            if (heroService.getHeroById(hero.getId()).isPresent()) {
                Assertions.fail();
            }
        } catch (Exception e) {
            Assertions.fail(e.toString());
        }
    }

    @Test
    public void tryRemoveNotExistHero() {
        try {
            heroService.removeById(111);
            Assertions.fail();
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
        }
    }

    @Test
    public void tryGetHeroByName() {
        try {
            Hero hero = heroService.add("heroName");

            Optional<Hero> heroByName = heroService.getHeroByName(hero.getName());
            if (!hero.getId().equals(heroByName.get().getId())) {
                Assertions.fail();
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }


    @Test
    public void tryRenameHero() {
        Hero hero = heroService.add("heroName");

        try {
            heroService.editHeroName(hero.getId(), "newName");
            Assertions.assertFalse(!heroService.getHeroByName("newName").isPresent());
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }

    @After
    public void afterEach() {
        heroService.deleteAll();
    }
}
