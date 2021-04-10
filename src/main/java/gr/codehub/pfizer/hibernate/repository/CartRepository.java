package gr.codehub.pfizer.hibernate.repository;

import gr.codehub.pfizer.hibernate.model.Cart;

import javax.persistence.EntityManager;

public class CartRepository extends Repository<Cart, Integer>{
    public CartRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Cart> getEntityClass() {
        return Cart.class;
    }

    @Override
    public String getClassName() {
        return Cart.class.getName();
    }
}
