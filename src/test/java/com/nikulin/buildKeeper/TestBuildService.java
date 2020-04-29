package com.nikulin.buildKeeper;

import com.nikulin.buildKeeper.configs.TestJpaConfig;
import com.nikulin.buildKeeper.controllers.dtos.BaseDto;
import com.nikulin.buildKeeper.controllers.dtos.BuildDto;
import com.nikulin.buildKeeper.dal.entities.Build;
import com.nikulin.buildKeeper.dal.entities.Hero;
import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.enums.Ability;
import com.nikulin.buildKeeper.enums.HeroPosition;
import com.nikulin.buildKeeper.services.BuildService;
import com.nikulin.buildKeeper.services.HeroService;
import com.nikulin.buildKeeper.services.ItemService;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestJpaConfig.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext
public class TestBuildService {
    @Autowired
    private BuildService buildService;

    @Autowired
    private HeroService heroService;

    @Autowired
    private ItemService itemService;

    @Test
    public void tryAddBuild() {
        try {
            Build build = createTestBuild();

            BuildDto buildDto = new BuildDto();
            buildDto.setName(build.getName());
            buildDto.setHero(new BaseDto(build.getHero().getId()));
            buildDto.setHeroPosition(build.getHeroPosition());


            List<BaseDto> items = new ArrayList<>();
            for (Item i : build.getItems()) {
                items.add(new BaseDto(i.getId()));
            }
            buildDto.setItems(items);

            buildDto.setAbilities(build.getAbilitiesString());

            buildDto.setRunes1(build.getRune1String());
            buildDto.setRunes2(build.getRune2String());
            buildDto.setRunes3(build.getRune3String());

            Build build1 = buildService.add(buildDto);
            if (!build1.getHero().getId().equals(build.getHero().getId())) {
                Assertions.fail();
            }

            if (!build1.getName().equals(build.getName())) {
                Assertions.fail();
            }

            if (!build1.getHeroPosition().equals(build.getHeroPosition())) {
                Assertions.fail();
            }

            if (!build1.getAbilitiesString().equals(build.getAbilitiesString())) {
                Assertions.fail();
            }

            if (build1.getItems().size() != build.getItems().size()) {
                Assertions.fail();
            }

            if (!buildService.getById(build1.getId()).isPresent()) {
                Assertions.fail();
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void tryRemoveBuild() {
        try {
            Build build = createTestBuild();

            BuildDto buildDto = new BuildDto();
            buildDto.setName(build.getName());
            buildDto.setHero(new BaseDto(build.getHero().getId()));
            buildDto.setHeroPosition(build.getHeroPosition());


            List<BaseDto> items = new ArrayList<>();
            for (Item i : build.getItems()) {
                items.add(new BaseDto(i.getId()));
            }
            buildDto.setItems(items);

            buildDto.setAbilities(build.getAbilitiesString());

            buildDto.setRunes1(build.getRune1String());
            buildDto.setRunes2(build.getRune2String());
            buildDto.setRunes3(build.getRune3String());

            Build build1 = buildService.add(buildDto);

            buildService.removeById(build1.getId());

            if (buildService.getById(build1.getId()).isPresent()) {
                Assertions.fail();
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }


    @Test
    public void tryUpdateBuild() {

        try {
            Build build = createTestBuild();

            BuildDto buildDto = new BuildDto();
            buildDto.setName(build.getName());
            buildDto.setHero(new BaseDto(build.getHero().getId()));
            buildDto.setHeroPosition(build.getHeroPosition());


            List<BaseDto> items = new ArrayList<>();
            for (Item i : build.getItems()) {
                items.add(new BaseDto(i.getId()));
            }
            buildDto.setItems(items);

            buildDto.setAbilities(build.getAbilitiesString());

            buildDto.setRunes1(build.getRune1String());
            buildDto.setRunes2(build.getRune2String());
            buildDto.setRunes3(build.getRune3String());

            Build build1 = buildService.add(buildDto);

            buildDto.setId(build1.getId());
            buildDto.setName("updatedName");

            buildService.updateBuild(buildDto);

            if (!buildService.getById(build1.getId()).get().getName().equals(buildDto.getName())) {
                Assertions.fail();
            }
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    private Build createTestBuild() {

        Hero hero = heroService.add(new Hero("Hero"));

        Item item = itemService.add("boots");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        List<Ability> abilities = new ArrayList<>();
        abilities.add(Ability.Q);

        Build build = new Build();
        build.setName("name");
        build.setHero(hero);
        build.setAbilities(abilities);
        build.setHeroPosition(HeroPosition.MIDDLE);
        build.setItems(itemList);
        build.setRune1("1-1-1-1-1");
        build.setRune2("1-1-1-1");
        build.setRune3("1-1-1");

        return build;
    }

    @After
    public void afterEach() {
        buildService.deleteAll();
        itemService.deleteAll();
        heroService.deleteAll();
    }
}