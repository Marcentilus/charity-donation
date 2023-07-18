package pl.coderslab.charity.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
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
import pl.coderslab.charity.dto.UserPasswordDto;
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
        model.addAttribute("user", new UserDto());

        return "user/register";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") @Valid UserDto user, @NotNull BindingResult result){
        if(result.hasErrors()){
            return "user/register";
        }
            userService.saveUser(user);

        return "index";
    }

    @GetMapping("/editPassword/{userId}")
    public String showEditPasswordForm(@PathVariable("userId") long id, Model model){


        model.addAttribute("userId", id);

        return "user/changePassword";
    }
    @PostMapping("/editPassword/{userId}")
    public String changePassword(@PathVariable("userId") long id, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Model model, HttpServletRequest request) throws ServletException {
        UserPasswordDto userPasswordDto = UserPasswordDto.builder()
                .oldPassword(oldPassword)
                .newPassword(newPassword)
                .build();
        boolean passwordValidation;

        try {
            passwordValidation = userService.editPassword(id, userPasswordDto);
        } catch (ResponseStatusException e){
            return "404";
        }
        if(!passwordValidation){
            model.addAttribute("message", "password does not match");
            return "user/changePassword";
        }
        request.logout();

        return "user/passwordChangeConfirmation";

    }
    @PostMapping("/edit/{userId}")
    public String editUser(@ModelAttribute("user") @Valid UserEditDto userEditDto, BindingResult result, @PathVariable long userId){
        if(result.hasErrors()){
            return "user/userDetails";
        }

        userService.editUser(userEditDto, userId);

        return "index";
    }

    @GetMapping("/details/{id}")
    public String showUserDetails(Model model, @PathVariable long id, @AuthenticationPrincipal CurrentUser currentUser){

        if(currentUser != null) {
            model.addAttribute("userId", currentUser
                    .getUser()
                    .getId());
        }
        try {
            model.addAttribute("user", userService.getUserEditDTO(id));
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
        return "redirect:/";
    }


}
