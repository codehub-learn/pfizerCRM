package gr.codehub.pfizer.hibernate;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Customer;
import gr.codehub.pfizer.hibernate.repository.CustomerRepository;

import javax.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CustomerRepository customerRepository  = new CustomerRepository(em);

        Customer    customer = new Customer();
        customer.setName("Marios G");
        customer.setAddress("Thessaloniki");
        customer.setEmail("marios@gmail.com");
        customer.setUsername("marios2");

        System.out.println(customer);
        customerRepository.save(customer);


        System.out.println(customer);
    }

}
