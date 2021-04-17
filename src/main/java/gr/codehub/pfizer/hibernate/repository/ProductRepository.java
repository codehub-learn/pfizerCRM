package gr.codehub.pfizer.hibernate.repository;

import gr.codehub.pfizer.hibernate.model.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductRepository extends Repository<Product, Integer> {
    private EntityManager entityManager;


    public ProductRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public String getClassName() {
        return Product.class.getName();
    }

    public List<Product> getProducts(float from, float to) {
        return entityManager.createQuery("select p from Product p  where p.price >= :from1 and p.price <= :to1", Product.class)
                .setParameter("from1", from)
                .setParameter("to1", to)
                .getResultList();
    }


}
