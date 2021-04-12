package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Customer;
import gr.codehub.pfizer.hibernate.model.Product;
import gr.codehub.pfizer.hibernate.repository.CustomerRepository;
import gr.codehub.pfizer.hibernate.repository.ProductRepository;
import gr.codehub.pfizer.hibernate.representation.CustomerRepresentation;
import gr.codehub.pfizer.hibernate.representation.ProductRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class CustomerResource extends ServerResource {
    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public CustomerRepresentation getCustomer() {
        EntityManager em = JpaUtil.getEntityManager();
        CustomerRepository customerRepository = new CustomerRepository(em);
        Customer customer = customerRepository.read(id);

        CustomerRepresentation customerRepresentation = new CustomerRepresentation(customer);
        em.close();
        return customerRepresentation;

    }




}
