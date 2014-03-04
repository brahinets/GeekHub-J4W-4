package com.ysb.dao;

import com.ysb.entity.Film;
import com.ysb.services.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Film Data access object layer
 *
 * manipulating with database storage of Film entities
 * */


@Repository
public class FilmDAOimpl {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static List getAllFilms(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List films = session.createCriteria(Film.class).list();
        session.getTransaction().commit();
        session.close();

        return films;
    }

    public static Film getFilmByID(Integer id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Film result = (Film) session.get(Film.class, id);
        session.getTransaction().commit();
        session.close();

        return result;
    }

    public static void addOrUpdateFilm(Film film){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(film);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteFilm(Film film) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(film);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteFilmByID(Integer id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("DELETE FROM Film u WHERE u.id=?");
        query.setParameter(0, id);
        query.executeUpdate();
        session.close();
    }
}
