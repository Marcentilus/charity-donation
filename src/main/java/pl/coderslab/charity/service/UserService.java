package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user){

        return userRepository.save(user);

    }

    public void deleteUserById(long id){

        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(long id){

        return userRepository.findById(id);
    }
}
