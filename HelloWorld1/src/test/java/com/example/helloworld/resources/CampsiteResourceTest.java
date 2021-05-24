package com.example.helloworld.resources;

import com.example.helloworld.HelloWorldApplication;
import com.example.helloworld.HelloWorldConfiguration;
import com.example.helloworld.hibernate.Campsite;
import com.example.helloworld.resources.CampsiteResource;
import com.example.helloworld.db.CampsiteDAO;


import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit.DropwizardAppRule;
import io.dropwizard.testing.junit5.ResourceExtension;

import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;




@ExtendWith(DropwizardExtensionsSupport.class)

public class CampsiteResourceTest {

    private static final CampsiteDAO DAO= mock(CampsiteDAO.class);
    public static final ResourceExtension RULE = ResourceExtension.builder()
            .addResource(new CampsiteResource(DAO))
            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
            .build();




    private Campsite site1;
    private Campsite site2;
    private Campsite site3;
    private List<Campsite> expectedList = new java.util.ArrayList<Campsite>();


    @BeforeEach
    void setup() {

        this.site1 = new Campsite("s1", "MO");
        this.site2 = new Campsite("s2", "co");
        this.site3 = new Campsite("s3","Boulder,CO");
        expectedList.add(this.site1);
        expectedList.add(this.site2);
        site1.setId(1L);
    }

    @AfterEach
    void tearDown() {
        reset(DAO);
    }

    @Test
    public void getAllSitesSuccess() {

        when(DAO.findAll()).thenReturn(expectedList);
        List<Campsite> found = DAO.findAll();
        assertThat(expectedList).isEqualTo(found);

    }

    @Test
    public void getSiteSuccess() {
        when(DAO.findById(1L)).thenReturn(site1);

        Campsite found = RULE.target("/campsites/1").request().get(Campsite.class);

        assertThat(found.getId()).isEqualTo(site1.getId());
        verify(DAO).findById(1L);
    }

    @Test
    public void addSiteSuccess() {

        when(DAO.findAll()).thenReturn(expectedList);

        DAO.addSite(this.site3);
        expectedList.add(this.site3);

        List<Campsite> found = DAO.findAll();
        assertThat(expectedList).isEqualTo(found);


    }
}
