package pl.coderslab.charity.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DonationDto {

    @NotEmpty
    private List<Category> categories;

    @NotNull
    private Institution institution;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private boolean collected;

    private LocalDateTime dateAdded;

    @NotNull
    private LocalTime pickUpTime;

    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String phone;
    @Positive
    private int quantity;

    private String pickUpComment;

    public DonationDto(){}

    public Donation getDonationFromDto(){
        return Donation.builder()
                .phone(this.getPhone())
                .city(this.getCity())
                .createdOn(this.getDateAdded())
                .categories(this.getCategories())
                .pickUpComment(this.getPickUpComment())
                .pickUpDate(this.getPickUpDate())
                .street(this.getStreet())
                .pickUpDate(this.getPickUpDate())
                .institution(this.getInstitution())
                .pickUpTime(this.getPickUpTime())
                .quantity(this.getQuantity())
                .zipCode(this.getZipCode())
                .build();

    }


}
