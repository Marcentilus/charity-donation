package pl.coderslab.charity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entity.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DonationDetailsDto {

    private long id;

    private List<Category> categoryList = new ArrayList<>();

    private String institution;

    private LocalDate pickUpDate;

    private boolean collected;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate collectedOn;




}
