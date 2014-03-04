package com.ysb.entity;

import javax.persistence.*;
import java.io.Serializable;
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

@Entity
@Table(name = "actors")
public class Actor implements Serializable{

    @Id
    @Column(name = "actorId")
    @GeneratedValue
    private int actorId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "birthDate")
    private Date birthDate;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name="actors_films",
            joinColumns={@JoinColumn(name="actorId")},
            inverseJoinColumns={@JoinColumn(name="filmId")})
    private Set<Film> films = new HashSet<Film>();

    public Actor() {
    }

    public Actor(String firstName, String secondName, Date birthDate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
    }

    public Actor(Integer actorId, String firstName, String secondName, Date birthDate) {
        this.actorId= actorId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
    }

    public Actor(Integer actorId, String firstName, String secondName, Date birthDate, Set<Film> films) {
        this.actorId= actorId;
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

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
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

