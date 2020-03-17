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

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    // удобно когда метод добавления сущности потом возвращает новую созданную сущность обратно
    public Hero add(String heroName) {
        return heroRepository.save(new Hero(heroName));
    }

    public Hero add(Hero hero) {
        return heroRepository.save(hero);
    }

    public void removeById(Integer heroId) {
        heroRepository.deleteById(heroId);
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

//    public Optional<Hero> getHeroByName(String name) {
//        return heroRepository.;
//    }

//    public void editHeroName(UUID id, String newName) {
//        Iterable<Hero> list = heroRepository.findAll();
//        for (Hero eachHero : list) {
//            if (eachHero.getId().equals(id)) {
//                eachHero.setName(newName);
//            }
//        }
//    }
}
