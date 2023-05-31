package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final InstitutionService institutionService;

    private final DonationService donationService;
    @RequestMapping("/")
    public String homeAction(Model model){



        model.addAttribute("institutions",institutionService.findAllInstitutions());

         model.addAttribute("totalQuantity", donationService.getTotalDonationQty());

        model.addAttribute("totalDonations", donationService.getTotalDonations());



        return "index";
    }



}
