package com.example.helloworld;
import org.hibernate.Session;


import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.hibernate.Campsite;
import com.example.helloworld.db.CampsiteDAO;
import com.example.helloworld.hibernate.HibernateUtil;
import io.dropwizard.db.DataSourceFactory;

import java.util.List;


public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    private  HibernateBundle<HelloWorldConfiguration> hibernateBundle
            = new HibernateBundle<HelloWorldConfiguration>(Campsite.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };


        public static void main(final String[] args) throws Exception {
            new HelloWorldApplication().run(args);
        }


        public String getName() {
            return "hello-world";
        }


//        @Override
//        public void initialize(Bootstrap <HelloWorldConfiguration> bootstrap) {
//            bootstrap.addBundle(hibernateBundle);
//        }

        @Override
        public void run(HelloWorldConfiguration configuration,
                        Environment environment) {

            //create campsites
            Campsite site1 = new Campsite("Yogi", "Yosemite, CA");
            Campsite site2 = new Campsite("Red Feathers", "Colorado");

            final TemplateHealthCheck healthCheck =
                    new TemplateHealthCheck(configuration.getTemplate());

            final CampsiteDAO campsiteDAO = new CampsiteDAO(HibernateUtil.getSessionFactory());

            environment.healthChecks().register("template", healthCheck);


            final CampsiteResource resource = new CampsiteResource(
                    campsiteDAO
            );

            resource.addSite(site1);
            resource.addSite(site2);
            environment.jersey().register(resource);

            //Printing out database contents to terminal
            Session session1 = HibernateUtil.getSessionFactory().openSession();
            List< Campsite > sites = session1.createQuery("from Campsite", Campsite.class).list();
            sites.forEach(s -> System.out.println(s.getName()));
            session1.close();
        }
}
