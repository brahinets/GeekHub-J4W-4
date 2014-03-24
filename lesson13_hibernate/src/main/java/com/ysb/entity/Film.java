package com.ysb.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Film entity
 *
 * stores all data about one person FILM :
 *      String      - name
 *      Integer     - year
 *      Set<Film>   - list of famous actors who stared in this film
 * */


@Entity
@Table(name = "films")
public class Film implements Serializable{

    @Id
    @Column(name = "filmId")
    @GeneratedValue
    private int filmId;

    @Column(name = "filmName")
    private String name;

    @Column(name = "filmYear")
    private Integer year;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "films")
    private  Set<Actor> actors = new HashSet<Actor>();

    public Film() {
    }

    public Film(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public Film(Integer id, String name, Integer year) {
        this.filmId = id;
        this.name = name;
        this.year = year;
    }

    public Film(Integer id, String name, Integer year, Set<Actor> actors) {
        this.filmId = id;
        this.name = name;
        this.year = year;
        this.actors = actors;
    }

    public Set<Actor> getActors() {
        return this.actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}

