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
        router.attach("/ping", PingServerResource.class); //get
         return router;
    }


    public Router protectedResources(){
        Router router = new Router();
        router.attach("/product", ProductListResource.class); //get, post
        router.attach("/product/{id}", ProductResource.class); //get, put, delete

        router.attach("/customer", CustomerListResource.class);//get, post
        router.attach("/customer/{id}", CustomerResource.class);//get, put, delete


        //router.attach("/cart", CartListResource.class); //get, post
        //router.attach("/cart/{id}", CartResource.class);//get, put, delete

        //router.attach("/cart/{id}/product", CartProductListResource.class); //get, post
        //router.attach("/cart/{id}/product/{pid}", CartProductResource.class);//get, put, delete
        return router;

    }


}
