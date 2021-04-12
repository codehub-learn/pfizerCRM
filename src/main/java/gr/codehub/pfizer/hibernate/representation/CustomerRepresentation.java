package gr.codehub.pfizer.hibernate.representation;


import gr.codehub.pfizer.hibernate.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class CustomerRepresentation {

    private int id;

    private String name;
    private String address;
    private String username;
    private String password;
    private String email;


    private String uri;

    public CustomerRepresentation(Customer customer) {
        if (customer != null) {
            name = customer.getName();
            address = customer.getAddress();
            username = customer.getUsername();
            email = customer.getEmail();
            password = customer.getPassword();
             uri =  "http://localhost:9000/v1/customer/" + customer.getId();
        }

    }

    public Customer createCustomer() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setPassword(password);
        return customer;
    }



}
