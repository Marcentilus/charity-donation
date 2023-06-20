package pl.coderslab.charity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserEditDto {
    @NotBlank
    private String userName;

    @Email
    private String email;

    private boolean enabled;

    private boolean admin;


}
