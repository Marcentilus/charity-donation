package pl.coderslab.charity.entity;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import pl.coderslab.charity.dto.DonationDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Positive
    private int quantity;

    @NotEmpty
    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "institution_id")
    private  Institution institution;

    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String phone;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotNull
    private LocalTime pickUpTime;

    private String pickUpComment;

    @ManyToOne
    private User user;

    private LocalDateTime createdOn;

    @PrePersist
    public void prePersist(){
        this.createdOn = LocalDateTime.now();
    }

    public DonationDto getDonationAsDto(){

        return  DonationDto.builder()
                .categories(this.categories)
                .institution(this.institution.getName())
                .pickUpDate(this.pickUpDate)
                .dateAdded(this.createdOn)
                .build();

    }

}
