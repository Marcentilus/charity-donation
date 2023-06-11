package pl.coderslab.charity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import pl.coderslab.charity.dto.UserNameDto;
import pl.coderslab.charity.dto.UserPasswordDto;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String username;

    private boolean enabled;


    @OneToMany
    @JoinColumn(name = "user_id")
    @Builder.Default
    private List<Donation> donation = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public User() {

    }

    public UserNameDto getAsDTO(){
        return UserNameDto.builder()
                .userName(this.name)
                .email(this.username)
                .enabled(this.enabled)
                .build();
    }

    public UserPasswordDto getPasswordAsDTO(){
        return UserPasswordDto.builder()
                .oldPassword(this.password)
                .build();
    }
}
