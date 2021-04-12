package gr.codehub.pfizer.hibernate.representation;


import gr.codehub.pfizer.hibernate.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRepresentation {

    private int id;
    private String name;
    private int inventoryQuantity;
    private float price;

    private String uri;


    // mappers
    public ProductRepresentation( Product product) {
        if (product != null) {
            inventoryQuantity = product.getInventoryQuantity();
            name = product.getName();
            price = product.getPrice();
            uri = "http://localhost:9000/v1/product/" + product.getId();
        }
    }

    public Product createProduct() {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setInventoryQuantity(inventoryQuantity);
        return product;
    }

}
