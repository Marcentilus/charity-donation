package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String password;

    @Email
    private String email;


    @OneToMany
    @JoinColumn(name = "donation_id")
    private List<Donation> donation;



}
