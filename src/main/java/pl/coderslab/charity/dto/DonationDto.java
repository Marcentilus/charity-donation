package pl.coderslab.charity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.entity.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class DonationDto {

    private List<Category> categories;

    private String institution;

    private LocalDate pickUpDate;

    private boolean collected;

    private LocalDateTime dateAdded;

}
