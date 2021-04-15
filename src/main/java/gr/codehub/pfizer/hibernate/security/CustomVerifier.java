package gr.codehub.pfizer.hibernate.security;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Customer;
import gr.codehub.pfizer.hibernate.repository.CustomerRepository;
import org.restlet.Request;
import org.restlet.security.Role;
import org.restlet.security.SecretVerifier;

import javax.persistence.EntityManager;

public class CustomVerifier extends SecretVerifier {

    @Override
    public int verify(String username, char[] password) {
        //check db for user
        EntityManager em = JpaUtil.getEntityManager();
        CustomerRepository customerRepository =new CustomerRepository(em);

        Customer customer = customerRepository.getByUsername(username);
            if (customer==null)
                return SecretVerifier.RESULT_INVALID;
        String passwordInDb = customer.getPassword();
        if (compare(passwordInDb.toCharArray(), password) ) {
            Request request = Request.getCurrent();
            request.getClientInfo().getRoles().add
                    (new Role(   customer.getRole()  ));
            return SecretVerifier.RESULT_VALID;
        }
        return SecretVerifier.RESULT_INVALID;
    }
}
