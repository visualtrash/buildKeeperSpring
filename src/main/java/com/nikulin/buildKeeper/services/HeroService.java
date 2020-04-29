package com.nikulin.buildKeeper.services;

import com.nikulin.buildKeeper.dal.entities.Hero;
import com.nikulin.buildKeeper.dal.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    // удобно когда метод добавления сущности потом возвращает новую созданную сущность обратно
    public Hero add(String heroName) {
        return heroRepository.save(new Hero(heroName));
    }

    public Hero add(Hero hero) {
        return heroRepository.save(hero);
    }

    public void removeById(Integer id) {
        heroRepository.deleteById(id);
    }

    @Transactional
    public void removeByName(String heroName) {
        heroRepository.deleteByName(heroName);
    }

    public Iterable<Hero> getAll() {
        return heroRepository.findAll();
    }

    public Optional<Hero> getHeroById(Integer heroId) {
        return heroRepository.findById(heroId);
    }

    public Optional<Hero> getHeroByName(String name) {
        return heroRepository.getHeroByName(name);
    }

    public Hero editHeroName(Integer id, String newName) throws Exception {
        Optional<Hero> hero = heroRepository.findById(id);

        if (!hero.isPresent()) {
            throw new Exception("hero with id = " + id + " is not exists");
        }

        hero.get().setName(newName);
        return heroRepository.save(hero.get());
    }

    public void deleteAll() {
        heroRepository.deleteAll();
    }
}
