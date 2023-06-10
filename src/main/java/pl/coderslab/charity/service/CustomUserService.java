package pl.coderslab.charity.service;


import pl.coderslab.charity.entity.User;

public interface CustomUserService {

    User findByName(String name);

    User saveUser(User user);

    User saveAdmin(User uer);

}

