package gr.codehub.pfizer.hibernate.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public abstract class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String role;
}
