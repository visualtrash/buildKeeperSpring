package com.nikulin.buildKeeper.services;

import com.nikulin.buildKeeper.controllers.dtos.BaseDto;
import com.nikulin.buildKeeper.controllers.dtos.BuildDto;
import com.nikulin.buildKeeper.dal.entities.Build;
import com.nikulin.buildKeeper.dal.entities.Hero;
import com.nikulin.buildKeeper.dal.entities.Item;
import com.nikulin.buildKeeper.dal.repositories.BuildRepository;
import com.nikulin.buildKeeper.dal.repositories.HeroRepository;
import com.nikulin.buildKeeper.dal.repositories.ItemRepository;
import com.nikulin.buildKeeper.enums.Ability;
import com.nikulin.buildKeeper.enums.HeroPosition;
import com.nikulin.buildKeeper.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildService {

    @Autowired
    private BuildRepository buildRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Iterable<Build> getAll() {
        return buildRepository.findAll();
    }

    // (что такое входные данные)
    //1. на вход метод принимает объект спец. типа, внутри которого находятся данные, которые заполняет пользователь
    // (что делаем со входными данными и зачем, в общем, потом детально)
    //2. метод создает пустой билд и подставляет введеные пользователем данные в поля нового билда
    //2.1 поле ИМЯ взять из входных данных и положить в созданный билд
    //2.2 поле ПОЗИЦИЯ взять из входных данных и положить в созданный билд
    //2.3 поле ГЕРОЙ взять из входных параметров, взять его айди и по этому айди найти в базе героя, подставить найденного героя в билд
    //2.4 поле ИТЕМЫ взять из входных параметров, создать новый список итемов, для каждого итема из входящего списка найти в базе итемов по айди, добавить в новый список и добавить результ.список в билд
    //2.5 поле АБИЛИТИ взять из входных параметров,  положить в созданный билд
    // (что будет являться результатом выполнения метода)
    //3. сохранить в репозиторий результат (заполненный билд)
    public Build add(BuildDto buildDto) {
        Build build = new Build();

        build.setName(buildDto.getName());

        build.setHeroPosition(buildDto.getHeroPosition());

        build.setRune1(buildDto.getRunes1());
        build.setRune2(buildDto.getRunes2());
        build.setRune3(buildDto.getRunes3());

        Optional<Hero> heroById = heroRepository.findById(buildDto.getHero().getId());
        if (heroById.isPresent()) {
            build.setHero(heroById.get());
        } else throw new ValidationException("not found hero by id " + buildDto.getHero().getId());

        List<Item> items = new ArrayList<>();
        for (BaseDto item : buildDto.getItems()) {
            Optional<Item> item1 = itemRepository.findById(item.getId());
            if (item1.isPresent()) {
                items.add(item1.get());
            } else throw new ValidationException("not found item by id " + item.getId());
        }
        build.setItems(items);

        build.setAbilities(buildDto.getAbilities());

        return buildRepository.save(build);
    }

    public Build add(String name, HeroPosition heroPosition, Hero hero,
                     Iterable<Item> items, Iterable<Ability> abilities) {

        return buildRepository.save(new Build(name, hero, heroPosition, items, abilities));
    }

    public void removeById(Integer id) {
        buildRepository.deleteById(id);
    }

    public void remove(Build build) {
        buildRepository.delete(build);
    }

//TODO
// public void getById(Build build) {
//        buildRepository.delete(build);
//    }

//    TODO
//    public void updateBuild()
}
