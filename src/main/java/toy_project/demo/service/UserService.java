package toy_project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import toy_project.demo.dto.LoginRequest;
import toy_project.demo.dto.UserRegistrationRequest;
import toy_project.demo.entity.User;
import toy_project.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with id " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with username " + username));
    }

    public User registerUser(UserRegistrationRequest request) {
        // Check for duplicate username
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        // Check for duplicate email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        // 패스워드 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 새로운 사용자 생성
        User user = new User(request.getUsername(), request.getFullName(), request.getEmail(), encodedPassword);

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password.");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password.");
        }

        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    // Check for duplicate username
                    if (!user.getUsername().equals(updatedUser.getUsername()) &&
                            userRepository.findByUsername(updatedUser.getUsername()).isPresent()) {
                        throw new IllegalArgumentException("Username is already taken.");
                    }

                    // Check for duplicate email
                    if (!user.getEmail().equals(updatedUser.getEmail()) &&
                            userRepository.findByEmail(updatedUser.getEmail()).isPresent()) {
                        throw new IllegalArgumentException("Email is already in use.");
                    }

                    user.setUsername(updatedUser.getUsername());
                    user.setFullName(updatedUser.getFullName());
                    user.setEmail(updatedUser.getEmail());
                    user.setProfilePicture(updatedUser.getProfilePicture());

                    // Check if password is being updated
                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                        // Encrypt and update the password
                        String encodedPassword = passwordEncoder.encode(updatedUser.getPassword());
                        user.setPassword(encodedPassword);
                    }

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));
    }

    public User deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));
        userRepository.deleteById(id);
        return user;
    }
}