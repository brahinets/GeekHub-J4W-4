package com.ysb.services;

import com.ysb.dao.FilmDAOimpl;
import com.ysb.entity.Film;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Layer which get input all FILM data and manipulating with it
 * changing and work with database
 * */

@Service
public class FilmService {
    public void addOrUpdateFilm(Integer id, String name, Integer year) {
        Film film = new Film(name, year);

        /* if update */
        if(id != null){
            film.setFilmId(id);
        }

        FilmDAOimpl.addOrUpdateFilm(film);
    }

    public void deleteFilmByID(Integer id) {
        FilmDAOimpl.deleteFilmByID(id);
    }

    public void deleteFilm(Film film) {
        FilmDAOimpl.deleteFilm(film);
    }

    public List getAllFilms() {
        return FilmDAOimpl.getAllFilms();
    }

    public Film getFilmByID(Integer id) {
        return FilmDAOimpl.getFilmByID(id);
    }
}
