package gr.codehub.pfizer.hibernate.repository;

import gr.codehub.pfizer.hibernate.model.Customer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {


    private EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //Create , insert
   public Customer save(Customer t){
       try {
           entityManager.getTransaction().begin();
           entityManager.persist(t);
           entityManager.getTransaction().commit();
           return t;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }



    // Read select
    public Customer read(int id){
        Customer t = entityManager.find(Customer.class, id);
        return t;
    }

    public List<Customer> findAll(){
        return entityManager.createQuery("from Customer").getResultList();
    }


    // Update
   public Customer update( Customer t)
   {

       try {
           entityManager.getTransaction().begin();
           entityManager.persist(t);
           entityManager.getTransaction().commit();
           return t;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }


    // Delete
        public boolean delete(int id) {
            Customer customer = read(id);
            if (customer == null) {
                return false;
            }
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(customer);
                entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }



}
