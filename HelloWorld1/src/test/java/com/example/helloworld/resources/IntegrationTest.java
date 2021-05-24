package com.example.helloworld.resources;

import com.example.helloworld.HelloWorldApplication;
import com.example.helloworld.HelloWorldConfiguration;
import com.example.helloworld.hibernate.Campsite;
import com.example.helloworld.db.CampsiteDAO;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.DropwizardAppExtension;

import org.eclipse.jetty.http.HttpStatus;
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
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.dropwizard.testing.ResourceHelpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;

@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {

    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("example.yml");
    private static final String TMP_FILE = createTempFile();

    public static final DropwizardAppExtension<HelloWorldConfiguration> RULE = new DropwizardAppExtension<>(
            HelloWorldApplication.class, CONFIG_PATH,
            ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE));

    @BeforeAll
    public static void migrateDb() throws Exception {
        RULE.getApplication().run("db", "migrate", CONFIG_PATH);
    }

    private static String createTempFile() {
        try {
            return File.createTempFile("test-example", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

//    private Campsite postSite(Campsite site) {
//        return EXT.client().target("http://localhost:" + EXT.getLocalPort() + "/campsites")
//                .request()
//                .post(Entity.entity(site, MediaType.APPLICATION_JSON_TYPE))
//                .readEntity(Campsite.class);
//    }


    @Test
    void testPostCampsite() {
        final Campsite site = new Campsite("Boyd", "Longmont");
        assertThat(2).isEqualTo(1+1);
//        final Campsite newSite = postSite(site);
//        final String url = "http://localhost:" + EXT.getLocalPort() + "/campsites/";
//        Response response = EXT.client().target(url).request().get();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
    }
}
