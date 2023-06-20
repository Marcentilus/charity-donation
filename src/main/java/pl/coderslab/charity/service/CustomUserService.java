package pl.coderslab.charity.service;


import pl.coderslab.charity.dto.UserNameDto;
import pl.coderslab.charity.entity.User;

public interface CustomUserService {

    User findByName(String name);

    User saveUser(UserNameDto user);

}

