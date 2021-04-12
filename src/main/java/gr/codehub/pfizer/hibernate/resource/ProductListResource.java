package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Product;
import gr.codehub.pfizer.hibernate.repository.ProductRepository;
import gr.codehub.pfizer.hibernate.representation.ProductRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ProductListResource extends ServerResource {


    @Get("json")
    public List<ProductRepresentation> getProduct(){
        EntityManager em = JpaUtil.getEntityManager();
        ProductRepository productRepository = new ProductRepository(em);
        List<Product> products = productRepository.findAll();
        em.close();
//        List<ProductRepresentation> productRepresentationList =
//                products.stream()
//                .map(p-> new ProductResource(p)).
//                .collect(toList());

        List<ProductRepresentation> productRepresentationList = new ArrayList<>();
        for (Product p : products)
            productRepresentationList.add(new ProductRepresentation(p));

        return productRepresentationList;
    }

    //@Post


}
