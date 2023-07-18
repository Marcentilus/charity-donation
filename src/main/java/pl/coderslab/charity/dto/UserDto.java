package pl.coderslab.charity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
public class UserDto {

    long id;

    @NotBlank
    private String userName;

    @Size(min = 8)
    @NotBlank
    private String password;

    @Email
    private String email;

    private boolean enabled;

    private boolean admin;

    public UserDto(){

    }




}
