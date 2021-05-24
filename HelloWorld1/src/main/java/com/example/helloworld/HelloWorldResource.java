package com.example.helloworld;


import com.example.helloworld.api.Saying;
import com.example.helloworld.hibernate.Campsite;
import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.net.URI;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import com.example.helloworld.db.CampsiteDAO;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;
    private CampsiteDAO campsiteDAO;


    public HelloWorldResource(String template, String defaultName, CampsiteDAO campsiteDAO) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.campsiteDAO = campsiteDAO;

    }

    @Timed
    @POST
    public Response sayHello(Campsite site) {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();


        // start a transaction
        transaction = session.beginTransaction();

        // save the student objects
        session.save(site);

        // commit transaction
        transaction.commit();
        session.close();

        return Response.ok(site).build();
    }


    @GET
    public Response findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        List< Campsite > sites = session.createQuery("from Campsite", Campsite.class).list();
        session.close();
        return Response.ok(sites).build();
    }



}