package gr.codehub.pfizer.hibernate.repository;


import gr.codehub.pfizer.hibernate.model.Customer;

import javax.persistence.EntityManager;

public class CustomerRepository extends Repository<Customer, Integer>{


    public CustomerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class getEntityClass() {
        return Customer.class;
    }

    @Override
    public String getClassName() {
        return Customer.class.getName();
    }
}
