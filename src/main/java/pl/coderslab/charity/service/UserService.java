package pl.coderslab.charity.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.bcel.BcelAccessForInlineMunger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.dto.UserNameDto;
import pl.coderslab.charity.dto.UserPasswordDto;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements CustomUserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    public User findByName(String username){
        return userRepository.findUserByUsername(username);
    }



    @Override
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));

        return userRepository.save(user);

    }
    @Override
    public User saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));

        return userRepository.save(user);

    }


    public void deleteUserById(long id){


        userRepository.deleteById(id);
    }

    public User getUserById(long id) throws ResponseStatusException{


        return userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id: " + id + " was not found"));
    }


    public List<User> findAllUsers(){

        return userRepository.findAll();
    }

    public UserNameDto getUserNameDTO(long id){

        return getUserById(id).getAsDTO();
    }

    public UserPasswordDto getUserPasswordDTO(long id){
        return getUserById(id).getPasswordAsDTO();
    }

    public void editUser(UserNameDto userNameDto) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(userNameDto.getEmail());

        user.setName(userNameDto.getUserName());
        user.setUsername(userNameDto.getEmail());
        user.setEnabled(userNameDto.isEnabled());

        userRepository.save(user);

    }

    public boolean editPassword( String username, UserPasswordDto userPasswordDto){

        User user = userRepository.findUserByUsername(username);

        if(user == null){
            return false;
        }

        if(!passwordEncoder.matches(userPasswordDto.getOldPassword(), user.getPassword())){
            return false;
        }

        user.setPassword(passwordEncoder.encode(userPasswordDto.getNewPassword()));

        userRepository.save(user);

        return true;
    }
}
