package pl.coderslab.charity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class UserNameDto {

    @NotBlank
    private String userName;

    private String email;

    private boolean enabled;
}
