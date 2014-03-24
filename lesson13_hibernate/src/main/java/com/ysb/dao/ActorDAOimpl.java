package com.ysb.dao;

import com.ysb.entity.Actor;
import com.ysb.services.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Actor Data access object layer
 *
 * manipulating with database storage of Actor entities
 * */


@Repository
public class ActorDAOimpl {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static List getAllActors(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List actors = session.createCriteria(Actor.class).list();
        session.getTransaction().commit();
        session.close();

        return actors;
    }

    public static Actor getActorByID(Integer id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Actor result = (Actor) session.get(Actor.class, id);
        session.getTransaction().commit();
        session.close();

        return result;
    }

    public static void addOrUpdateActor(Actor actor){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(actor);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteActor(Actor actor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(actor);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteActorByID(Integer id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("DELETE FROM Actor u WHERE u.id=?");
        query.setParameter(0, id);
        query.executeUpdate();
        session.close();
    }
}
