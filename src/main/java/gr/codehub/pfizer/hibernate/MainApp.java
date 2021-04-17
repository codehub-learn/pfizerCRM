package gr.codehub.pfizer.hibernate;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.router.CustomRouter;
import gr.codehub.pfizer.hibernate.security.CorsFilter;
import gr.codehub.pfizer.hibernate.security.Shield;
import gr.codehub.pfizer.hibernate.service.Business;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Role;

import javax.persistence.EntityManager;
import java.util.logging.Logger;


public class MainApp extends Application {


    public static final Logger LOGGER = Engine.getLogger(MainApp.class);



    public MainApp(){
        setName("WebAPITutorial");
        setDescription("Full Web API tutorial");

        getRoles().add(new Role(this, Shield.ROLE_ADMIN));
        getRoles().add(new Role(this, Shield.ROLE_OWNER));
        getRoles().add(new Role(this, Shield.ROLE_USER));

    }



    public static void main(String[] args) throws Exception{


        ///////////////////////////////

        EntityManager em = JpaUtil.getEntityManager();

        //Business.testMe(em);

        em.close();
        //////////////////////////////////////


        LOGGER.info("Contacts application starting...");




        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/v1", new MainApp());
        c.start();

        LOGGER.info("Sample Web API started");
        LOGGER.info("URL: http://localhost:9000/v1/product");
    }

    @Override
    public Restlet createInboundRoot() {

        CustomRouter customRouter = new CustomRouter(this);
        Shield shield = new Shield(this);
        ChallengeAuthenticator apiGuard = shield.createApiGuard();


        Router publicResources = customRouter.publicResources();
        Router protectedResources = customRouter.protectedResources();

        publicResources.attachDefault(apiGuard);
        apiGuard.setNext(protectedResources);


        CorsFilter corsFilter = new CorsFilter(this);
        return corsFilter.createCorsFilter(publicResources);
    }





}
