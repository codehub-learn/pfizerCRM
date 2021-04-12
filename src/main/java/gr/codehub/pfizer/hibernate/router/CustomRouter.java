package gr.codehub.pfizer.hibernate.router;

import gr.codehub.pfizer.hibernate.resource.*;
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
        router.attach("/product", ProductListResource.class);
        router.attach("/product/{id}", ProductResource.class);

      router.attach("/customer", CustomerListResource.class);
        router.attach("/customer/{id}", CustomerResource.class);

        return router;
    }


}
