package pl.coderslab.charity.dto;

import lombok.Builder;
import lombok.Data;
import org.eclipse.tags.shaded.org.apache.xpath.objects.XString;

@Builder
@Data
public class UserPasswordDto {

    private String oldPassword;

    private String newPassword;
}
