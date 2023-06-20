package pl.coderslab.charity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String userName;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Email
    private String email;

    private boolean enabled;

    private boolean admin;

    public UserDto(){

    }




}
