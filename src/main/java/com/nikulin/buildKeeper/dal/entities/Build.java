package com.nikulin.buildKeeper.dal.entities;

import com.nikulin.buildKeeper.enums.Ability;
import com.nikulin.buildKeeper.enums.HeroPosition;
import com.nikulin.buildKeeper.exceptions.ValidationException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.nikulin.buildKeeper.enums.Ability.*;

@Data
@Entity
@Table(name = "builds")
public class Build implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_id", nullable = false)
    private Hero hero;

    @Column(name = "hero_position")
    private HeroPosition heroPosition;

    @ManyToMany
    @JoinTable(name = "build_item",
            joinColumns = @JoinColumn(name = "build_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> items;

    @Column(name = "abilities")
    @Setter(AccessLevel.NONE)
    private String abilities;

    @Column(name = "rune_set_1")
    @Setter(AccessLevel.NONE)
    private String rune1;

    @Column(name = "rune_set_2")
    @Setter(AccessLevel.NONE)
    private String rune2;

    @Column(name = "rune_set_3")
    @Setter(AccessLevel.NONE)
    private String rune3;

    public Build(String name, Hero hero, HeroPosition heroPosition, Iterable<Item> items, Iterable<Ability> abilities) {
        this.name = name;
        this.hero = hero;
        this.heroPosition = heroPosition;
        this.items = (List<Item>) items;
        this.abilities = String.valueOf(abilities);
    }

    // get(list) + set(void ->list -> string)
    public List<Ability> getAbilities() {
        String[] strings = abilities.split("-");

        List<Ability> list = new ArrayList<>();
        for (String abilityString : strings) {
            switch (abilityString) {
                case "Q":
                    list.add(Q);
                    break;
                case "W":
                    list.add(W);
                    break;
                case "E":
                    list.add(E);
                    break;
                case "R":
                    list.add(R);
                    break;
                default:
                    throw new ValidationException("incorrect ability " + abilityString);
            }
        }
        return list;
    }

    public void setAbilities(List<Ability> list) {
        List<String> result = new ArrayList<>();

        for (Ability ability : list) {
            switch (ability) {
                case Q:
                    result.add("Q");
                    break;
                case W:
                    result.add("W");
                    break;
                case E:
                    result.add("E");
                    break;
                case R:
                    result.add("R");
                    break;
                default:
                    throw new ValidationException("incorrect ability " + ability);
            }
        }
        this.abilities = String.join("-", result);
    }

    public void setAbilities(String abilities) {
        String[] s = abilities.split("-");

        for (String ability : s) {
            if (!ability.equals("Q") && !ability.equals("W") &&
                    !ability.equals("E") && !ability.equals("R")) {
                throw new ValidationException("incorrect ability " + ability);
            }
        }
        this.abilities = abilities;
    }

    public Build() {
    }

    public void setRune1(String rune) {
        validateRunes(rune);
        this.rune1 = rune;
    }

    public List<String> getRune1() {
        String[] strings = rune1.split("-");

        List<String> list = new ArrayList<>();
        for (String runeString : strings) {
            switch (runeString) {
                case "0":
                    list.add("0");
                    break;
                case "1":
                    list.add("1");
                    break;
                case "2":
                    list.add("2");
                    break;
                case "3":
                    list.add("3");
                    break;
                case "4":
                    list.add("4");
                    break;
                case "5":
                    list.add("5");
                    break;
                default:
                    throw new ValidationException("incorrect rune " + runeString);
            }
        }
        return list;
    }

    public void setRune2(String rune) {
        validateRunes(rune);
        this.rune2 = rune;
    }

    public List<String> getRune2() {
        String[] strings = rune2.split("-");


        List<String> list = new ArrayList<>();
        for (String runeString : strings) {
            switch (runeString) {
                case "0":
                    list.add("0");
                    break;
                case "1":
                    list.add("1");
                    break;
                case "2":
                    list.add("2");
                    break;
                case "3":
                    list.add("3");
                    break;
                case "4":
                    list.add("4");
                    break;
                default:
                    throw new ValidationException("incorrect rune " + runeString);
            }
        }
        return list;
    }

    public List<String> getRune3() {
        String[] strings = rune3.split("-");

        List<String> list = new ArrayList<>();
        for (String runeString : strings) {
            switch (runeString) {
                case "1":
                    list.add("1");
                    break;
                case "2":
                    list.add("2");
                    break;
                case "3":
                    list.add("3");
                    break;
                default:
                    throw new ValidationException("incorrect rune " + runeString);
            }
        }
        return list;
    }

    public void setRune3(String rune) {
        validateRunes(rune);
        this.rune3 = rune;
    }

    private void validateRunes(String rune) {
        String[] runeStrings = rune.split("-");

        for (String currentRune : runeStrings) {
            if (!currentRune.equals("0") && !currentRune.equals("1") &&
                    !currentRune.equals("2") && !currentRune.equals("3") && !currentRune.equals("4")) {
                throw new ValidationException("incorrect rune " + currentRune);
            }

        }
    }

    // TODO:
    //private Rune rune;
}

