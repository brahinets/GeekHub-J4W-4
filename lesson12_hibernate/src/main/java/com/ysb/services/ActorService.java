package com.ysb.services;

import com.ysb.dao.ActorDAOimpl;
import com.ysb.dao.FilmDAOimpl;
import com.ysb.entity.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Layer which get input all ACTOR data and manipulating with it
 * changing and work with database
 * */

@Service
public class ActorService {

    public void addOrUpdateActor(Integer id, String firstName, String secondName ,String birthDate, Integer films[]) {
        Actor actor = new Actor(firstName, secondName, java.sql.Date.valueOf(birthDate));

        /* all films of actor */
        if(films != null) {
            for (Integer film : films) {
                actor.getFilms().add(FilmDAOimpl.getFilmByID(film));
            }
        }

        /* if updating */
        if(id != null){
            actor.setActorId(id);
        }

        ActorDAOimpl.addOrUpdateActor(actor);
    }

    public void deleteActorByID(Integer id) {
        ActorDAOimpl.deleteActorByID(id);
    }

    public void deleteActor(Actor actor) {
        ActorDAOimpl.deleteActor(actor);
    }

    public List getAllActors() {
        return ActorDAOimpl.getAllActors();
    }

    public Actor getActorByID(Integer id) {
        return ActorDAOimpl.getActorByID(id);
    }
}
