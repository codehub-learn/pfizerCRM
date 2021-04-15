package gr.codehub.pfizer.hibernate.repository;

import gr.codehub.pfizer.hibernate.model.Cart;
import gr.codehub.pfizer.hibernate.model.Customer;

import javax.persistence.EntityManager;

public class CartRepository extends Repository<Cart, Integer>{

    private EntityManager entityManager;

    public CartRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Cart> getEntityClass() {
        return Cart.class;
    }

    @Override
    public String getClassName() {
        return Cart.class.getName();
    }




    public Customer getCustomer(int cartId){
        return entityManager.createQuery("SELECT cu FROM Cart c inner join c.customer cu WHERE c.id = :cartId",
                Customer.class)
                .setParameter("cartId", cartId)
                .getSingleResult();
    }
}
