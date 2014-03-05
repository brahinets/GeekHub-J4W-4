package com.ysb.model.service;

import com.ysb.model.dao.ActorDAOimpl;
import com.ysb.model.entities.Actor;

import java.util.Collection;
import java.util.List;

public class ActorService {

    /* get Actor object without @Ignored fields (List<Films>) from database */
    public static Actor getActorByID(Integer id) {
        try {
            ActorDAOimpl actorDAOimpl = new ActorDAOimpl();

            return actorDAOimpl.getByID(id);
        } catch (Exception e) {
            return null;
        }
    }


    /* get Actor object with @Ignored fields (List<Films>) from database */
    public static Actor getActorByIDFull(Integer id) {
        try {
            ActorDAOimpl actorDAOimpl = new ActorDAOimpl();
            Actor actor = actorDAOimpl.getByID(id);
            actor.setFilms(actorDAOimpl.getFilmsOfActor(id));

            return actor;
        } catch (Exception e) {
            return null;
        }
    }


    /* get Collection of Actor objects with @Ignored fields (List<Film>) from database */
    public static Collection getList() {
        try {
            ActorDAOimpl actorDAOimpl = new ActorDAOimpl();
            List<Actor> actors = actorDAOimpl.getAll();
            for(Actor actor : actors){
                actor.setFilms(actorDAOimpl.getFilmsOfActor(actor.getId()));
            }

            return actors;
        } catch (Exception e) {
            return null;
        }
    }


    /* save Actor object to database storage */
    public static void add(Actor actor)  {
        try {
            ActorDAOimpl actorDAOimpl = new ActorDAOimpl();
            actorDAOimpl.save(actor);
        } catch (Exception ignored) {
        }
    }


    /* save Actor object to database storage */
    public static void add(Integer id, String firstName, String secondName ,String birthDate, Integer films[])  {
        try {
            Actor actor = new Actor(firstName, secondName, java.sql.Date.valueOf(birthDate));

        /* all films of actor */
            if(films != null) {
                for (Integer film : films) {
                    actor.getFilms().add(FilmService.getFilmByID(film));
                }
            }

        /* if updating */
            if(id != null){
                actor.setId(id);
            }

            ActorDAOimpl actorDAOimpl = new ActorDAOimpl();
            actorDAOimpl.save(actor);
        } catch (Exception ignored) {
        }
    }


    /* remove Actor object from database storage by object ID */
    public static void deleteByID(Integer id) {
        ActorDAOimpl actorDAOimpl = new ActorDAOimpl();
        try {
            actorDAOimpl.delete(getActorByID(id));
        } catch (Exception ignored) {
        }
    }


    /* remove Film object from database storage by Object*/
    public static void delete(Actor actor)  {
        try {
            ActorDAOimpl actorDAOimpl = new ActorDAOimpl();
            actorDAOimpl.delete(actor);
        } catch (Exception ignored) {
        }
    }
}
