package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Product;
import gr.codehub.pfizer.hibernate.repository.ProductRepository;
import gr.codehub.pfizer.hibernate.representation.ProductRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProductListResource extends ServerResource {
//     Product  -> T     ProductRepresentation ->         ProductRepository -> Repository<T>

    @Get("json")
    public List<ProductRepresentation> getProduct(){
        EntityManager em = JpaUtil.getEntityManager();
        ProductRepository productRepository = new ProductRepository(em);
        List<Product> products = productRepository.findAll();
        em.close();
        List<ProductRepresentation> productRepresentationList =
                products.stream()
               .map( p-> new ProductRepresentation(p))
               .collect(toList());

        return productRepresentationList;
    }

    @Post("json")
    public ProductRepresentation add(ProductRepresentation productRepresentationIn){

        if (productRepresentationIn ==null) return null;
        if (productRepresentationIn.getName() == null) return null;

        Product product = productRepresentationIn.createProduct();
        EntityManager em = JpaUtil.getEntityManager();
        ProductRepository productRepository = new ProductRepository(em);
        productRepository.save(product);
        ProductRepresentation p = new ProductRepresentation(product);
        return p;
    }


}
