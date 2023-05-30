package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
public class HomeController {


    private final InstitutionService institutionService;
    @RequestMapping("/")
    public String homeAction(Model model){

        List<Institution> institutions = institutionService.findAllInstitutions();
        if(!Objects.isNull(institutions)){
            model.addAttribute("institutions", institutions);
        }

        return "index";
    }



}
