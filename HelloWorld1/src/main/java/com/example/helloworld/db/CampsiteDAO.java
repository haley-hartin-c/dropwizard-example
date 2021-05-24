package com.example.helloworld.db;

import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import com.example.helloworld.hibernate.Campsite;
import com.example.helloworld.hibernate.HibernateUtil;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static java.util.Collections.list;
import org.hibernate.query.Query;


public class CampsiteDAO extends AbstractDAO<Campsite>{

    public CampsiteDAO(SessionFactory sessionFactory) {
            super(sessionFactory);
    }

    public List<Campsite> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        List< Campsite > sites = session.createQuery("from Campsite", Campsite.class).list();
        session.close();
        return sites;


    }


    public void addSite(Campsite site){

        Session session2= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        // start a transaction
        transaction = session2.beginTransaction();

        session2.save(site);

        // commit transaction
        transaction.commit();
        session2.close();

    }

}
