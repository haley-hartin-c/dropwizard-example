package com.example.helloworld.lib;
import javax.ws.rs.NotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import com.example.helloworld.db.CampsiteDAO;
import com.example.helloworld.hibernate.Campsite;
import com.example.helloworld.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import io.dropwizard.jersey.params.LongParam;
import javax.ws.rs.PathParam;
import io.dropwizard.hibernate.UnitOfWork;


public class CampsiteLib {

    private CampsiteDAO campsiteDAO;

    public CampsiteLib(CampsiteDAO campsiteDAO) {
        this.campsiteDAO = campsiteDAO;
    }


    public void addSite(Campsite site) {
        campsiteDAO.addSite(site);
    }


    public List<Campsite> findAll() { return campsiteDAO.findAll(); }


    public Campsite getSite( Long siteId) {
        return findSafely(siteId);
    }

    private Campsite findSafely(long siteId) {
        return campsiteDAO.findById(siteId).orElseThrow(() -> new NotFoundException("No such campsite."));
    }
}

