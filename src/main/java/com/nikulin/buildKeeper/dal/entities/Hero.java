package com.nikulin.buildKeeper.dal.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "heroes")
public class Hero implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "img")
    private String img;

    public Hero(String heroName) {
        this.name = heroName;
    }

    public Hero() {

    }

}
