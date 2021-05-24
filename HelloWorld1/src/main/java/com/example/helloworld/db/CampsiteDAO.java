package com.example.helloworld.db;

import java.util.List;

import com.example.helloworld.hibernate.Campsite;
import com.example.helloworld.hibernate.HibernateUtil;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


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

    public Campsite findById(long siteId) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Campsite site = session.createQuery(
                "select c from Campsite c where c.siteId like :siteId", Campsite.class).setParameter("siteId", siteId).getSingleResult();;
        return site;
    }

}
