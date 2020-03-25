package com.nikulin.buildKeeper.dal.entities;

import com.nikulin.buildKeeper.enums.Ability;
import com.nikulin.buildKeeper.exceptions.ValidationException;
import lombok.Data;

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
    private String heroPosition;

    @ManyToMany
    @JoinTable(name="build_item",
            joinColumns = @JoinColumn(name="build_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="item_id", referencedColumnName="id")
    )
    private List<Item> items;

    @Column(name = "abilities")
    private String abilities;

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



    // TODO:
    //private Rune rune;
}
