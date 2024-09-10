package toy_project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
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
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}