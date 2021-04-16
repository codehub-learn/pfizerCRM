package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.representation.CustomerRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class RegisterResource extends ServerResource {
    @Post
    public CustomerRepresentation registerCustomer(CustomerRepresentation customerRepresentation){
        return null;
    }
}
