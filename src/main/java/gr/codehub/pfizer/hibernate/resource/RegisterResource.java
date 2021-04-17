package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Customer;
import gr.codehub.pfizer.hibernate.repository.CustomerRepository;
import gr.codehub.pfizer.hibernate.representation.CustomerRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class RegisterResource extends ServerResource {

    @Post
    public ApiResult<CustomerRepresentation> registerCustomer(CustomerRepresentation customerRepresentation) {

        if (customerRepresentation == null)
            return new ApiResult<>(null, 400, "No input data to create the customer");
        if (customerRepresentation.getName() == null)
            return new ApiResult<>(null, 400, "No name was given to create the customer");
        if (customerRepresentation.getUsername() == null)
                return new ApiResult<>(null, 400, "No username was given to create the customer");
        if (usernameExists(customerRepresentation.getUsername()))
            return new ApiResult<>(null, 400, "Duplicate username");

        Customer customer = customerRepresentation.createCustomer();
        EntityManager em = JpaUtil.getEntityManager();
        CustomerRepository customerRepository = new CustomerRepository(em);
        customerRepository.save(customer);
        return new ApiResult<>(new CustomerRepresentation(customer), 200,
                "The customers was successfully created");
    }

    public boolean usernameExists(String candidateUsername) {
        EntityManager em = JpaUtil.getEntityManager();
        Customer c = null;
        try {
            c = em.createQuery("SELECT u from Customer u where u.username= :candidate", Customer.class)
                    .setParameter("candidate", candidateUsername)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return c != null;
    }

    @Get
    public boolean usernameExists() {
        String candidateUsername = "";

        try {
            candidateUsername = getQueryValue("username");
        } catch (Exception e) {
            return false;
        }
        return usernameExists(candidateUsername);
    }


}
