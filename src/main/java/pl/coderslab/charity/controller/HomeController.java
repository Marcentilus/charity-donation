package pl.coderslab.charity.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;


@Controller
@RequiredArgsConstructor
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final InstitutionService institutionService;

    private final DonationService donationService;

    private final UserService userService;
    @RequestMapping("/")
    public String homeAction(Model model, @AuthenticationPrincipal CurrentUser currentUser, HttpSession session){

      /*  User admin = User.builder()
                .username("admin@charity.pl")
                .password("YT-1300$Corellian")
                .build();
        userService.saveAdmin(admin);*/

        if(currentUser != null) {
            model.addAttribute("userId", currentUser
                    .getUser()
                    .getId());
            session.setAttribute("username", currentUser
                    .getUser()
                    .getName());
        }

        model.addAttribute("institutions", institutionService.findFirstFourInstitutions());

        model.addAttribute("totalQuantity", donationService.getTotalDonationQty());

        model.addAttribute("totalDonations", donationService.getTotalDonations());



        return "index";
    }



}
