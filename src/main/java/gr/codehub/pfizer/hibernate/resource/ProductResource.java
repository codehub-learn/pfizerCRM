package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Product;
import gr.codehub.pfizer.hibernate.repository.ProductRepository;
import gr.codehub.pfizer.hibernate.representation.ProductRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductResource extends ServerResource {
    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public ProductRepresentation getProduct() {
        EntityManager em = JpaUtil.getEntityManager();
        ProductRepository productRepository = new ProductRepository(em);
        Product product = productRepository.read(id);
        ProductRepresentation productRepresentation2 = new ProductRepresentation(product);
        em.close();
        return productRepresentation2;
    }


    @Put("json")
public ProductRepresentation putProduct(ProductRepresentation productRepresentation) {

        EntityManager em = JpaUtil.getEntityManager();
        ProductRepository productRepository = new ProductRepository(em);
        Product product = productRepository.read(id);

        product.setPrice(productRepresentation.getPrice());
        product.setName(productRepresentation.getName());

        productRepository.save(product);

        ProductRepresentation productRepresentation2 = new ProductRepresentation(product);
        em.close();
        return productRepresentation2;

    }


  @Delete("txt")
  public boolean deleteProduct() {
      EntityManager em = JpaUtil.getEntityManager();
      ProductRepository productRepository = new ProductRepository(em);
      return productRepository.delete(id);
  }

}
