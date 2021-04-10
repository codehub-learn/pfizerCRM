package gr.codehub.pfizer.hibernate;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Customer;
import gr.codehub.pfizer.hibernate.model.Product;
import gr.codehub.pfizer.hibernate.repository.CustomerRepository;
import gr.codehub.pfizer.hibernate.repository.ProductRepository;

import javax.persistence.EntityManager;

public class MainApp {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CustomerRepository customerRepository  = new CustomerRepository(em);

        Customer customer = new Customer();
        customer.setName("Marios G");
        customer.setAddress("Thessaloniki");
        customer.setEmail("marios@gmail.com");
        customer.setUsername("marios2");

        System.out.println(customer);
        customerRepository.save(customer);
        System.out.println(customer);

        ProductRepository productRepository= new ProductRepository(em);
        Product product = new Product();

        product.setName("Chocolate");
        product.setPrice(2);
        product.setInventoryQuantity(10);

        System.out.println(product);
        productRepository.save(product);
        System.out.println(product);



        em.close();
    }

}
