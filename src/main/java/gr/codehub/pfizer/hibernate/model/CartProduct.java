package gr.codehub.pfizer.hibernate.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class CartProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Product product;

}
