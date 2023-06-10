package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionService institutionService;

    private final UserService userService;

    @GetMapping("/institution/list")
    public String showInstitutionList(Model model){

        model.addAttribute("institutions", institutionService.findAllInstitutions());

        return "institution/institutionList";
    }

    @GetMapping("/institution/add")
    public String showInstitutionForm(Model model){

        model.addAttribute("institution", new Institution());

        return "institution/institutionAdd";
    }

    @PostMapping("/institution/add")
    public String addInstitution(@Valid Institution institution, BindingResult result){
        if(result.hasErrors()){
            return "institution/institutionAdd";
        }

        institutionService.addInstitution(institution);

        return "institution/institutionList";
    }

    @GetMapping("/institution/edit/{id}")
    public String showInstitutionEditForm(@PathVariable long id, Model model){

        model.addAttribute("institution", institutionService.findById(id));

        return "institution/institutionEdit";
    }

    @PostMapping("/institution/edit")
    public String editInstitution(@Valid Institution institution, BindingResult result){
        if(result.hasErrors()){
            return "institution/institutionEdit";
        }

        institutionService.addInstitution(institution);

        return "redirect:/admin/institution/list";
    }

    @GetMapping("/institution/delete/{id}")
    public String deleteInstitution(@PathVariable long id){

        institutionService.deleteInstitution(id);

        return "redirect:/admin/institution/list";
    }
    @GetMapping("/institution/confirm-delete/{id}")
    public String confirmInstitutionDelete(@PathVariable long id, Model model){
        model.addAttribute("id", id);
        return "institution/deleteConfirmation";
    }

    @GetMapping("/user/list")
    public String showUserList(Model model){
        model.addAttribute("users", userService.findAllUsers());

        return "user/userList";
    }

    @GetMapping("user/edit/{id}")
    public String adminEditUser(@PathVariable long id,Model model){

        model.addAttribute("user",userService.getUserById(id));

        return "user/adminEdit";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteUserById(id);

        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/confirm-delete/{id}")
    public String confirmUserDelete(@PathVariable long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/deleteConfirmation";
    }


}
