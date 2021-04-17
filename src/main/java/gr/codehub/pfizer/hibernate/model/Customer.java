package gr.codehub.pfizer.hibernate.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Customer extends Person{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;

}
