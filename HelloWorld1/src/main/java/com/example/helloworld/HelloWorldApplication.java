package com.example.helloworld;
import com.example.helloworld.resources.CampsiteResource;
import org.hibernate.Session;


import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.hibernate.Campsite;
import com.example.helloworld.db.CampsiteDAO;
import com.example.helloworld.hibernate.HibernateUtil;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.migrations.MigrationsBundle;
import com.example.helloworld.lib.CampsiteLib;

import java.util.List;
import lombok.Data;

@Data
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

        private String name = "hello-world";

        private  HibernateBundle<HelloWorldConfiguration> hibernateBundle
                = new HibernateBundle<HelloWorldConfiguration>(Campsite.class) {

            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return  configuration.getDataSourceFactory();
            }

        };

        @Override
        public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
            bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {

                @Override
                public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration  configuration) {
                    return configuration.getDataSourceFactory();

                }
            });

        }


        public static void main(final String[] args) throws Exception {
            new HelloWorldApplication().run(args);
        }

        @Override
        public void run(HelloWorldConfiguration configuration,
                        Environment environment) {

            //create campsites
            Campsite site1 = new Campsite("Yogi", "Yosemite, CA");
            Campsite site2 = new Campsite("Red Feathers", "Colorado");

            final TemplateHealthCheck healthCheck =
                    new TemplateHealthCheck(configuration.getTemplate());

            final CampsiteDAO campsiteDAO = new CampsiteDAO(HibernateUtil.getSessionFactory());
            final CampsiteLib campsiteLib = new CampsiteLib(campsiteDAO);
            final CampsiteResource resource = new CampsiteResource(campsiteLib);


            resource.addSite(site1);
            resource.addSite(site2);

            environment.healthChecks().register("template", healthCheck);
            environment.jersey().register(resource);

            //Printing out database contents to terminal
            Session session = HibernateUtil.getSessionFactory().openSession();
            List< Campsite > sites = session.createQuery("from Campsite", Campsite.class).list();
            sites.forEach(s -> System.out.println(s.getName()));

        }



}
