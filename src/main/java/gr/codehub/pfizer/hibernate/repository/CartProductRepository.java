package gr.codehub.pfizer.hibernate.repository;

import gr.codehub.pfizer.hibernate.model.CartProduct;

import javax.persistence.EntityManager;

public class CartProductRepository extends Repository<CartProduct, Integer> {



    public CartProductRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CartProduct> getEntityClass() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }
}
