package pl.coderslab.charity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.coderslab.charity.entity.Donation;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class UserDonationDto {

    long userId;

    private Donation donation;
}
