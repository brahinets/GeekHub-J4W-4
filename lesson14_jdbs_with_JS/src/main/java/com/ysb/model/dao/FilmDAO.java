package com.ysb.model.dao;

import com.ysb.model.entities.Actor;
import com.ysb.model.entities.Film;

import java.util.List;
import java.util.Set;

/**
 * Implementation of FilmDAO that uses database as a storage for objects.
 * It uses reflection to access objects fields and retrieve data to map to database tables.
 * As an identifier it uses field id of Entity class.
 */
public interface FilmDAO {

    /**
     * Retrieves List of Actors who played in film with specified ID from storage
     *
     * @param filmId identifier of film.
     * @return list os new instances of Film or null if it is not find nothing
     * @throws Exception
     */
    public Set<Actor> getActorsToFilm(Integer filmId) throws Exception;


    /**
     * Retrieves Film from storage by its identifier.
     *
     * @param id identifier of that object.
     * @return new instance of Film or null if it is not find by id.
     * @throws Exception
     */
    public Film getByID(Integer id) throws Exception;


    /**
     * Retrieves all Film from storage.
     * @return getAll of objects of Film or empty getAll if storage does not contains any object.
     * @throws Exception
     */
    public List<Film> getAll() throws Exception;


    /**
     * Deletes film from storage.
     * @param entity that will be deleted
     * @throws Exception
     */
    public void delete(Film entity) throws Exception;


    /**
     * If Film does not exists in storage yet it will be created, otherwise it will be updated.
     * @param entity that will be stored in storage.
     * @throws Exception
     */
    public void save(Film entity) throws Exception;



    /**
     * Get actors count who played in film
     * @param film
     * @throws Exception
     */
    public Integer getActorsCount(Film film) throws Exception;
}