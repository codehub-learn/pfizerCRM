package gr.codehub.pfizer.hibernate.representation;

import gr.codehub.pfizer.hibernate.model.Cart;
import lombok.Data;

import java.util.Date;

@Data
public class CartRepresentation {
    private int id;

    private Date date;
    private int customerId;
    private String uri;


    public CartRepresentation(Cart cart){
        if (cart!=null){
            date= cart.getDate();
            if (cart.getCustomer()!=null)
                customerId = cart.getCustomer().getId();
            uri =  "http://localhost:9000/v1/cart/" + cart.getId();
        }
    }




}
