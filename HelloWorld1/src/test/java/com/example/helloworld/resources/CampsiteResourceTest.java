package com.example.helloworld.resources;

import com.example.helloworld.HelloWorldApplication;
import com.example.helloworld.HelloWorldConfiguration;
import com.example.helloworld.hibernate.Campsite;
import com.example.helloworld.db.CampsiteDAO;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;



@ExtendWith(DropwizardExtensionsSupport.class)

public class CampsiteResourceTest {

    @ClassRule
    public static final DropwizardAppRule<HelloWorldConfiguration> DROPWIZARD =
            new DropwizardAppRule(HelloWorldApplication.class, "example.yaml");


    private static final CampsiteDAO DAO= mock(CampsiteDAO.class);


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
    }

    @AfterEach
    void tearDown() {
        reset(DAO);
    }

    @Test
    public void getSiteSuccess() {

        when(DAO.findAll()).thenReturn(expectedList);
        List<Campsite> found = DAO.findAll();
        assertThat(expectedList).isEqualTo(found);

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
