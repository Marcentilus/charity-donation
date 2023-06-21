package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.dto.UserDonationDto;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

    private final UserService userService;

    private final InstitutionService institutionService;

    private final CategoryService categoryService;

    private final DonationService donationService;

    @ModelAttribute
    public List<Institution> institutions(){return institutionService.findAllInstitutions();}

    @ModelAttribute
    public List<Category> categories(){ return categoryService.findAllCategories();}

    @GetMapping("/add")
    public String showForm(Model model, @AuthenticationPrincipal CurrentUser currentUser){

        model.addAttribute("userId", 0L);
        if(currentUser != null){
            model.addAttribute("userId", currentUser
                    .getUser()
                    .getId());
        }


        model.addAttribute("donation", new Donation());

        return "donation/donationForm";
    }

    @PostMapping("/add/{userId}")
    public String processForm(@Valid Donation donation, BindingResult result, @PathVariable long userId){

        if(result.hasErrors()){
            return "donation/donationForm";
        }
        donationService.addDonation(donation);

        if(userId > 0){
            UserDonationDto userDonationDto = UserDonationDto.builder()
                    .donation(donation)
                    .userId(userId)
                    .build();

            userService.addDonationToUser(userDonationDto);
        }

        return "donation/formConfirmation";
    }

    @GetMapping("/list/{id}")
    public String donationList(@PathVariable long id, Model model){
        model.addAttribute("donations", donationService.findDonationsByUser(id));

        return "user/donationList";
    }

    @GetMapping("/details/{id}")
    public String donationDetails(@PathVariable long id, Model model){

        try {
            model.addAttribute("donation", donationService.findDonationById(id));
        } catch (ResponseStatusException e){
            return "404";
        }

      return "user/donation";
    }

}
