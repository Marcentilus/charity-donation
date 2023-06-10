package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
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

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable long id, Model model){


        model.addAttribute("user", userService.getUserById(id));

        return "user/edit";
    }

    @PostMapping("/edit")
    public String editUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "user/edit";
        }

        userService.saveUser(user);

        return "index";
    }


}
