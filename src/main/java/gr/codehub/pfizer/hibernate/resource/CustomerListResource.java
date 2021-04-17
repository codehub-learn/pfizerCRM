package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Customer;
import gr.codehub.pfizer.hibernate.model.Product;
import gr.codehub.pfizer.hibernate.repository.CustomerRepository;
import gr.codehub.pfizer.hibernate.repository.ProductRepository;
import gr.codehub.pfizer.hibernate.representation.CustomerRepresentation;
import gr.codehub.pfizer.hibernate.representation.ProductRepresentation;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CustomerListResource extends ServerResource {


    @Post("json")
    public CustomerRepresentation add(CustomerRepresentation customerRepresentation){

        //authorization check

        //validations
        if (customerRepresentation ==null) return null;
        if (customerRepresentation.getName() == null) return null;

        //save to Db
        Customer customer = customerRepresentation.createCustomer();
        EntityManager em = JpaUtil.getEntityManager();
        CustomerRepository customerRepository = new CustomerRepository(em);
        customerRepository.save(customer);
        return new CustomerRepresentation(customer);
    }


    @Get("json")
    public ApiResult<List<CustomerRepresentation>> getCustomers() {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_USER);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        //reading getQueryParams

        //get customers
        EntityManager em = JpaUtil.getEntityManager();
        CustomerRepository customerRepository = new CustomerRepository(em);

        List<Customer> customers = customerRepository.findAll();
        em.close();

        //convert to representation
        List<CustomerRepresentation> customerRepresentationList =
                customers.stream()
                        .map(CustomerRepresentation::new)
                        .collect(toList());
        return new ApiResult<>(customerRepresentationList, 200, "ok");
    }
}
