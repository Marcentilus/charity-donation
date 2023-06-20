package pl.coderslab.charity.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.dto.UserDonationDto;
import pl.coderslab.charity.dto.UserDto;
import pl.coderslab.charity.dto.UserEditDto;
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
    public User saveUser(UserDto userDto){

        User user = new User();
        Role userRole;

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getUserName());
        user.setUsername(userDto.getEmail());
        user.setEnabled(true);
        if(userDto.isAdmin()) {
            userRole = roleRepository.findByName("ROLE_ADMIN");
            user.setAdmin(true);
        } else {
            userRole = roleRepository.findByName("ROLE_USER");
        }
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

    public UserEditDto getUserEditDTO(long id){

        return getUserById(id).getAsDTO();
    }

    public UserPasswordDto getUserPasswordDTO(long id){
        return getUserById(id).getPasswordAsDTO();
    }

    public void editUser(UserEditDto userEditDto) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(userEditDto.getEmail());

        user.setName(userEditDto.getUserName());
        user.setUsername(userEditDto.getEmail());
        user.setEnabled(userEditDto.isEnabled());

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

    public void addDonationToUser(UserDonationDto userDonationDto) throws ResponseStatusException{
        Optional<User> userOptional = userRepository.findById(userDonationDto.getUserId());
           User user = userOptional.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
           user.getDonation()
                   .add(userDonationDto.getDonation());
           userRepository.save(user);
    }
}
