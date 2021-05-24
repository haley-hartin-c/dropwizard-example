package com.example.helloworld.resources;
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


@Path("/campsites")
@Produces(MediaType.APPLICATION_JSON)
public class CampsiteResource {


    private CampsiteDAO campsiteDAO;

    public CampsiteResource(CampsiteDAO campsiteDAO) {
        this.campsiteDAO = campsiteDAO;
    }

    @POST
    public Response addSite(Campsite site) {
        campsiteDAO.addSite(site);
        return Response.ok(site).build();
    }


    @GET
    @UnitOfWork
    public List<Campsite>  findAll() {
        return campsiteDAO.findAll();
    }

    @GET
    @UnitOfWork
    @Path("/{siteId}")
    public Campsite getPerson(@PathParam("siteId") Long siteId) {
        return campsiteDAO.findById(siteId);
    }


}

