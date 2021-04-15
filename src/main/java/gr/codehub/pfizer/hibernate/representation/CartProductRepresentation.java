package gr.codehub.pfizer.hibernate.representation;

import gr.codehub.pfizer.hibernate.model.CartProduct;
import lombok.Data;

import java.util.Date;

@Data
public class CartProductRepresentation {

    private int id;
    private int productId;
    private int cartId;
    private String uri;

    public CartProductRepresentation(){

    }

    public CartProductRepresentation(CartProduct cartProduct){
        if (cartProduct!= null) {
            id = cartProduct.getId();
            if (cartProduct.getProduct()!=null)
                productId = cartProduct.getProduct().getId();
            if (cartProduct.getCart()!=null)
                cartId = cartProduct.getCart().getId();
            uri =  "http://localhost:9000/v1/cartproduct/" + cartProduct.getId();
        }

    }



}
