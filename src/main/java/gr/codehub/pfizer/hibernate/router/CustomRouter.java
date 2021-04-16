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
        router.attach("/register", RegisterResource.class); //post

         return router;
    }


    public Router protectedResources(){
        Router router = new Router();
        router.attach("/product", ProductListResource.class); //get all, post
        router.attach("/product/{id}", ProductResource.class); //get one, put, delete

        router.attach("/customer", CustomerListResource.class);//get all, post
        router.attach("/customer/{id}", CustomerResource.class);//get one, put, delete


//        router.attach("/cart", CartListResource.class); //get all, post
//        router.attach("/cart/{id}", CartResource.class);//get one, put, delete
//
//        router.attach("/customer/{id}/cart/", OrderResource.class); //get all, post
//        router.attach("/customer/{id}/cart/{id}", OrderResource.class); //get one, put, delete
//
//        router.attach("/cart/{id}/product", CartProductListResource.class); //get all, post
//        router.attach("/cart/{id}/product/{pid}", CartProductResource.class);//get one, put, delete








        return router;

    }


}
