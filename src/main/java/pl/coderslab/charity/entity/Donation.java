package pl.coderslab.charity.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import pl.coderslab.charity.dto.DonationDetailsDto;
import pl.coderslab.charity.dto.DonationDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "donations")
@Builder
@AllArgsConstructor
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

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    private LocalDateTime createdOn;

    private boolean collected;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    private LocalDateTime deactivatedOn;

    public Donation(){

    }

    @PrePersist
    public void prePersist(){
        this.createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){ this.deactivatedOn = LocalDateTime.now();}

    public DonationDto getDonationAsDto(){

        return  DonationDto.builder()
                .categories(this.categories)
                .institution(this.institution)
                .pickUpDate(this.pickUpDate)
                .dateAdded(this.createdOn)
                .city(this.city)
                .phone(this.phone)
                .zipCode(this.zipCode)
                .street(this.street)
                .pickUpComment(this.pickUpComment)
                .build();
    }

    public DonationDetailsDto getDonationDetails(){

        return DonationDetailsDto.builder()
                .id(id)
                .categoryList(categories)
                .institution(institution.getName())
                .pickUpDate(pickUpDate)
                .createdOn(createdOn)
                .collected(collected)
                .collectedOn(deactivatedOn)
                .build();
    }


}
