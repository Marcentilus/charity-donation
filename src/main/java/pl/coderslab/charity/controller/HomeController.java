package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final InstitutionService institutionService;

    private final DonationService donationService;
    @RequestMapping("/")
    public String homeAction(Model model){



        model.addAttribute("institutions", institutionService.findFirstFourInstitutions());

         model.addAttribute("totalQuantity", donationService.getTotalDonationQty());

        model.addAttribute("totalDonations", donationService.getTotalDonations());



        return "index";
    }



}
