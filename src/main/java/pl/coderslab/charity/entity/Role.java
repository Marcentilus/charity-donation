package pl.coderslab.charity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

}
