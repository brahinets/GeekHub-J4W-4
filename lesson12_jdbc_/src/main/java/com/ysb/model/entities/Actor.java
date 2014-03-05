package com.ysb.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Actor entity
 *
 * stores all data about one person ACTOR :
 *      String      - name
 *      String      - surname
 *      Date        - birth day
 *      Set<Film>   - list of films on which he\she played
 * */

public class Actor extends Entity{

    private String firstName;
    private String secondName;
    private Date birthDate;

    @Ignore
    private Set<Film> films = new HashSet<Film>();

    public Actor() {
    }

    public Actor(String firstName, String secondName, Date birthDate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
    }

    public Actor(Integer actorId, String firstName, String secondName, Date birthDate) {
        this.setId(actorId);
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
    }

    public Actor(Integer actorId, String firstName, String secondName, Date birthDate, Set<Film> films) {
        this.setId(actorId);
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.films  = films;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
