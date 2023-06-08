package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

    private final InstitutionService institutionService;

    private final CategoryService categoryService;

    private final DonationService donationService;

    @ModelAttribute
    public List<Institution> institutions(){return institutionService.findAllInstitutions();}

    @ModelAttribute
    public List<Category> categories(){ return categoryService.findAllCategories();}

    @GetMapping("/add")
    public String showForm(Model model){
        model.addAttribute("donation", new Donation());

        return "donationForm";
    }

    @PostMapping("/add")
    public String processForm(Donation donation, BindingResult result){

        if(result.hasErrors()){
            return "donationForm";
        }
        donationService.addDonation(donation);

        return "formConfirmation";
    }

}
