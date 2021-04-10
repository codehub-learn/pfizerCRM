package gr.codehub.pfizer.hibernate.repository;

import gr.codehub.pfizer.hibernate.model.Product;

import javax.persistence.EntityManager;

public class ProductRepository extends Repository<Product, Integer> {
    public ProductRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public String getClassName() {
        return Product.class.getName();
    }
}
