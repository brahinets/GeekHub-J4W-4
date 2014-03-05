package com.ysb.model.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Film entity
 *
 * stores all data about one FILM :
 *      String      - name
 *      Integer     - year
 *      Set<Film>   - list of actors who played in this film
 * */


public class Film extends Entity{

    private String name;
    private Integer year;

    @Ignore
    private Collection<Actor> actors = new HashSet<Actor>();

    public Film() {
    }

    public Film(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public Film(Integer id, String name, Integer year) {
        this.setId(id);
        this.name = name;
        this.year = year;
    }

    public Film(Integer id, String name, Integer year, Set<Actor> actors) {
        this.setId(id);
        this.name = name;
        this.year = year;
        this.actors = actors;
    }

    public Collection<Actor> getActors() {
        return this.actors;
    }

    public void setActors(Collection<Actor> actors) {
        this.actors = actors;
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