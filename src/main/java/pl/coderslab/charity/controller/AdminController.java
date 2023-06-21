package pl.coderslab.charity.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.dto.UserDto;
import pl.coderslab.charity.dto.UserEditDto;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.security.CurrentUser;
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
    public String showUserList(Model model, @AuthenticationPrincipal CurrentUser currentUser){

        if(currentUser != null){
            model.addAttribute("currentUserId", currentUser
                    .getUser()
                    .getId());
        }

        model.addAttribute("users", userService.findAllUsers());

        return "user/userList";
    }

    @GetMapping("user/edit/{id}")
    public String adminEditUser(@PathVariable long id,Model model){

        try {
            model.addAttribute("userId", id);
            model.addAttribute("user", userService.getUserEditDTO(id));
            model.addAttribute("userPassword", userService.getUserPasswordDTO(id));
        } catch(ResponseStatusException e){
            return "404";
        }

        return "user/adminEdit";
    }
    @PostMapping("user/edit/{userId}")
    public String editUser(@Valid UserEditDto userEditDto, BindingResult result, @PathVariable long userId){
        if(result.hasErrors()){
            return "user/userDetails";
        }


        try {
            userService.editUser(userEditDto, userId);
        } catch (ResponseStatusException e){
            return "404";
        }

        return "index";
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

    @GetMapping("/add")
    public String showAdminForm(Model model){

        UserDto admin = UserDto.builder()
                        .admin(true)
                                .build();

        model.addAttribute("user", admin);


        return "user/adminRegister";
    }

    @PostMapping("/add")
    public String addAdmin(@Valid UserDto user, @NotNull BindingResult result){
        if(result.hasErrors()){
            return "user/adminRegister";
        }
        userService.saveUser(user);

        return "index";
    }

}
