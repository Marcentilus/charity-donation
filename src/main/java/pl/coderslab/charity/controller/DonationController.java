package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.dto.DonationDto;
import pl.coderslab.charity.dto.UserDonationDto;
import pl.coderslab.charity.entity.Category;
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


        model.addAttribute("donation", new DonationDto());

        return "donation/donationForm";
    }

    @PostMapping("/add/{userId}")
    public String processForm(@Valid DonationDto donationDto, BindingResult result, @PathVariable long userId){

        if(result.hasErrors()){
            return "donation/donationForm";
        }
        UserDonationDto userDonationDto = donationService.addDonation(donationDto, userId);

        if(userId > 0){

            try {
                userService.addDonationToUser(userDonationDto);
            } catch (ResponseStatusException e){
                return "404";
            }
        }

        return "donation/formConfirmation";
    }

    @GetMapping("/list/{id}")
    public String donationList(@PathVariable long id, Model model){
        model.addAttribute("donations", donationService.findDonationsByUser(id));
        model.addAttribute("userId", id);

        return "donation/donationList";
    }

    @GetMapping("/details/{donationId}/{userId}")
    public String donationDetails(@PathVariable long donationId, @PathVariable long userId, Model model){

        try {
            model.addAttribute("donation", donationService.findDonationDetailsById(donationId));
            model.addAttribute("userId", userId);
        } catch (ResponseStatusException e){
            return "404";
        }

      return "donation/donation";
    }

    @GetMapping("/deactivate/{donationId}")
    public String deactivateDonation(@PathVariable long donationId, @AuthenticationPrincipal CurrentUser currentUser){


        if(currentUser != null) {
            long userId = currentUser
                    .getUser()
                    .getId();
            try {
                donationService.deactivateDonation(donationId);
            } catch (ResponseStatusException e) {
                return "404";
            }
            return "redirect:/donation/details/" + donationId + "/" + userId;
        }
        return "index";

    }

}
