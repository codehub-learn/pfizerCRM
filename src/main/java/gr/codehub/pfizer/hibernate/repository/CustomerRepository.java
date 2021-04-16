package gr.codehub.pfizer.hibernate.repository;


import gr.codehub.pfizer.hibernate.model.Cart;
import gr.codehub.pfizer.hibernate.model.Customer;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


public class CustomerRepository extends Repository<Customer, Integer>{

    private EntityManager entityManager;
    public CustomerRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
     }

    @Override
    public Class getEntityClass() {
        return Customer.class;
    }

    @Override
    public String getClassName() {
        return Customer.class.getName();
    }


        //JPQL
    public Customer getByUsername(String username){
        return entityManager.createQuery("SELECT b FROM Customer b " +
                "WHERE b.username = :username", Customer.class)
                .setParameter("username", username)
                .getSingleResult();
    }



    public List<Cart> getCarts(int customerId, Date from, Date to){
            return entityManager.createQuery("SELECT c  FROM Customer cu inner join cu.carts c" +
                            " WHERE cu.id = :customerId and c.date >:from and c.date <:to",
                    Cart.class)
                    .setParameter("customerId", customerId)
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getResultList();
     }

    public List<Cart> getCarts(int customerId ){
        return entityManager.createQuery("SELECT c  FROM Customer cu inner join cu.carts c" +
                        " WHERE cu.id = :customerId  ",
                Cart.class)
                .setParameter("customerId", customerId)
                 .getResultList();

    }


}
