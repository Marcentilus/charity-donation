package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;


}

