package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Product;
import gr.codehub.pfizer.hibernate.repository.ProductRepository;
import gr.codehub.pfizer.hibernate.representation.ProductRepresentation;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProductListResource extends ServerResource {
//     Product  -> T     ProductRepresentation ->         ProductRepository -> Repository<T>

    @Get("json")


    public ApiResult<List<ProductRepresentation>> getProduct()   {

        //authorisation check

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_USER);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        int fromPrice =0;
        int toPrice = 100000;

        // check for query params
        try {
            fromPrice = Integer.parseInt(getQueryValue("from"));
            toPrice = Integer.parseInt(getQueryValue("to"));
        }
        catch(Exception e){

        }


        EntityManager em = JpaUtil.getEntityManager();
        ProductRepository productRepository = new ProductRepository(em);

        List<Product> products = productRepository.getProducts((float)fromPrice, (float)toPrice);
        em.close();

        List<ProductRepresentation> productRepresentationList =
                products.stream()
                 .map( p-> new ProductRepresentation(p))
               .collect(toList());

        return new ApiResult<>(productRepresentationList, 200, "ok");
    }

    @Post("json")
    public ProductRepresentation add(ProductRepresentation productRepresentationIn){

        if (productRepresentationIn ==null) return null;
        if (productRepresentationIn.getName() == null) return null;

        Product product = productRepresentationIn.createProduct();
        EntityManager em = JpaUtil.getEntityManager();
        ProductRepository productRepository = new ProductRepository(em);
        productRepository.save(product);
        return new ProductRepresentation(product);

    }


}
