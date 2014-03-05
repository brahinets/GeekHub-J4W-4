package com.ysb.model.service;

import com.ysb.model.dao.FilmDAOimpl;
import com.ysb.model.entities.Film;
import java.util.Collection;
import java.util.List;

public class FilmService {

    /* get Film object without @Ignored fields (List<Actors>) from database */
    public static Film getFilmByID(Integer id) {
        FilmDAOimpl filmDAOimpl = new FilmDAOimpl();

        try {
            return filmDAOimpl.getByID(id);
        } catch (Exception e) {
            return null;
        }
    }


    /* get Film object with @Ignored fields (List<Actors>) from database */
    public static Film getFilmByIDFull(Integer id) {
        FilmDAOimpl filmDAOimpl = new FilmDAOimpl();

        try {
            Film film = filmDAOimpl.getByID(id);
            film.setActors(filmDAOimpl.getActorsToFilm(id));

            return film;
        } catch (Exception e) {
            return null;
        }
    }


    /* get Collection of Film objects without @Ignored fields (List<Actors>) from database */
    public static Collection getList() {
        try {
            FilmDAOimpl filmDAOimpl = new FilmDAOimpl();
            return filmDAOimpl.getAll();
        } catch (Exception e) {
            return null;
        }
    }


    /* get Collection of Film objects with @Ignored fields (List<Actors>) from database */
    public static Collection getListFull() {
        try {
            FilmDAOimpl filmDAOimpl = new FilmDAOimpl();
            List<Film> films = filmDAOimpl.getAll();

            for(Film film : films){
                film.setActors(filmDAOimpl.getActorsToFilm(film.getId()));
            }

            return films;
        } catch (Exception e) {
            return null;
        }
    }


    /* save Film object to database storage */
    public static void add(Film film)  {
        try {
            FilmDAOimpl filmDAOimpl = new FilmDAOimpl();
            filmDAOimpl.save(film);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* remove Film object from database storage by object ID */
    public static void deleteByID(Integer id) {
        try {
            FilmDAOimpl filmDAOimpl = new FilmDAOimpl();
            filmDAOimpl.delete(getFilmByID(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* remove Film object from database storage by Object*/
    public static void deleteFilm(Film film)  {
        try {
            FilmDAOimpl filmDAOimpl = new FilmDAOimpl();
            filmDAOimpl.delete(film);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
