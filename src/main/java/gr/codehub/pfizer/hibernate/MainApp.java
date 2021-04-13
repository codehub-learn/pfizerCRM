package gr.codehub.pfizer.hibernate;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.router.CustomRouter;
import gr.codehub.pfizer.hibernate.service.Business;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.engine.application.CorsFilter;
import org.restlet.routing.Router;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Logger;


public class MainApp extends Application {


    public static final Logger LOGGER = Engine.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception{

        LOGGER.info("Contacts application starting...");

        EntityManager em = JpaUtil.getEntityManager();
        em.close();


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
        Router publicRouter = customRouter.publicResources();

        CorsFilter corsFilter = new CorsFilter(getContext(),publicRouter);
        corsFilter.setAllowedCredentials(true);
        corsFilter.setAllowedOrigins(new HashSet<>(Arrays.asList("*")));
        HashSet<Method> methodHashSet = new HashSet<>();
        methodHashSet.add(Method.GET);
        methodHashSet.add(Method.POST);
        methodHashSet.add(Method.PUT);
        methodHashSet.add(Method.DELETE);
        corsFilter.setDefaultAllowedMethods(methodHashSet);

        return corsFilter;
    }

}
