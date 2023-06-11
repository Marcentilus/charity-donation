package pl.coderslab.charity.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.dto.UserNameDto;
import pl.coderslab.charity.dto.UserPasswordDto;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.security.CurrentUser;
import pl.coderslab.charity.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/add")
    public String showUserForm(Model model){
        model.addAttribute("user", new User());

        return "user/register";
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "user/register";
        }

        userService.saveUser(user);

        return "index";
    }

    @GetMapping("/editPassword/{username}")
    public String showEditPasswordForm(@PathVariable String username, Model model){


        model.addAttribute("username", username);

        return "user/changePassword";
    }
    @PostMapping("/editPassword/{username}")
    public String changePassword(@PathVariable String username, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Model model){
        UserPasswordDto userPasswordDto = UserPasswordDto.builder()
                .oldPassword(oldPassword)
                .newPassword(newPassword)
                .build();
        boolean passwordValidation = userService.editPassword(username, userPasswordDto);
        if(!passwordValidation){
            model.addAttribute("message", "password does not match");
            return "user/changePassword";
        }

        return "user/passwordChangeConfirmation";

    }
    @PostMapping("/edit")
    public String editUser(@Valid UserNameDto userNameDto, BindingResult result){
        if(result.hasErrors()){
            return "user/userDetails";
        }

        userService.editUser(userNameDto);

        return "index";
    }



    @GetMapping("/details/{id}")
    public String showUserDetails(Model model, @PathVariable long id){

        try {
            model.addAttribute("user", userService.getUserNameDTO(id));
            model.addAttribute("userPassword", userService.getUserPasswordDTO(id));
        } catch(ResponseStatusException e){
            return "404";
        }

        return "user/userDetails";
    }

    @GetMapping("/validation")
    public String validateUser(@AuthenticationPrincipal CurrentUser currentUser, HttpServletRequest request) throws ServletException {
        if(currentUser != null){
            if(!currentUser.getUser().isEnabled()){
                request.logout();
                return "user/blocked";
            }
        }
        return "/";
    }


}
