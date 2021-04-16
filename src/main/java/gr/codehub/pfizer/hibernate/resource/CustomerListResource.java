package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Customer;
import gr.codehub.pfizer.hibernate.repository.CustomerRepository;
import gr.codehub.pfizer.hibernate.representation.CustomerRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class CustomerListResource extends ServerResource {


    @Post("json")
    public CustomerRepresentation add(CustomerRepresentation customerRepresentation){


        //authorization check


        if (customerRepresentation ==null) return null;
        if (customerRepresentation.getName() == null) return null;

        Customer customer = customerRepresentation.createCustomer();
        EntityManager em = JpaUtil.getEntityManager();
        CustomerRepository customerRepository = new CustomerRepository(em);
        customerRepository.save(customer);
        return new CustomerRepresentation(customer);

    }


}
