package gr.codehub.pfizer.hibernate;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Cart;
import gr.codehub.pfizer.hibernate.model.CartProduct;
import gr.codehub.pfizer.hibernate.model.Customer;
import gr.codehub.pfizer.hibernate.model.Product;
import gr.codehub.pfizer.hibernate.repository.CartProductRepository;
import gr.codehub.pfizer.hibernate.repository.CartRepository;
import gr.codehub.pfizer.hibernate.repository.CustomerRepository;
import gr.codehub.pfizer.hibernate.repository.ProductRepository;
import gr.codehub.pfizer.hibernate.services.Business;

import javax.persistence.EntityManager;
import java.util.Date;

public class MainApp {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        Business.createData(em);
        em.close();
    }


}
