package com.nikulin.buildKeeper.dal.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "heroes")
public class Hero implements Serializable {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", unique = true)
    private String name;

    public Hero(String heroName) {
        this.name = heroName;
    }

    public Hero() {

    }

}
