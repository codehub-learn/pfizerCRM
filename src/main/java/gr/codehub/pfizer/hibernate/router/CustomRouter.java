package gr.codehub.pfizer.hibernate.router;

import gr.codehub.pfizer.hibernate.resource.PingServerResource;
import gr.codehub.pfizer.hibernate.resource.ProductResource;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {


    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/ping", PingServerResource.class);
        router.attach("/product", ProductResource.class);


        return router;
    }


}
