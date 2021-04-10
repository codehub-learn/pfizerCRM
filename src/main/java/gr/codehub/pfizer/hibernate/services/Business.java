package gr.codehub.pfizer.hibernate.services;

import gr.codehub.pfizer.hibernate.model.Cart;
import gr.codehub.pfizer.hibernate.model.CartProduct;
import gr.codehub.pfizer.hibernate.model.Customer;
import gr.codehub.pfizer.hibernate.model.Product;
import gr.codehub.pfizer.hibernate.repository.CartProductRepository;
import gr.codehub.pfizer.hibernate.repository.CartRepository;
import gr.codehub.pfizer.hibernate.repository.CustomerRepository;
import gr.codehub.pfizer.hibernate.repository.ProductRepository;

import javax.persistence.EntityManager;
import java.util.Date;

public class Business {


    public static void createData(EntityManager em ) {
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



        Cart cart = new Cart();
        CartRepository cartRepository = new CartRepository(em);

        cart.setCustomer(customer);
        cart.setDate( new Date());

        CartProductRepository cartProductRepository = new CartProductRepository(em);
        CartProduct cartProduct= new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setCart(cart);

        cartProductRepository.save(cartProduct);
        cartRepository.save(cart);


    }


}
