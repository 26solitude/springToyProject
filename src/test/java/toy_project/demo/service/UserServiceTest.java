package toy_project.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import toy_project.demo.dto.UserRegistrationRequest;
import toy_project.demo.entity.User;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        UserRegistrationRequest user = new UserRegistrationRequest();
        user.setUsername("test_user");
        user.setFullName("Test User");
        user.setEmail("test.user@example.com");
        user.setPassword("testpassword");

        User savedUser = userService.registerUser(user);

        assertNotNull(savedUser.getId());
        assertEquals("test_user", savedUser.getUsername());
    }

    @Test
    public void testGetUserById() {
        UserRegistrationRequest user = new UserRegistrationRequest();
        user.setUsername("test_user");
        user.setFullName("Test User");
        user.setEmail("test.user@example.com");
        user.setPassword("testpassword");

        User savedUser = userService.registerUser(user);

        User foundUser = userService.getUserById(savedUser.getId());
        assertNotNull(foundUser, "User should not be null");
        assertEquals("test_user", foundUser.getUsername());
    }

    @Test
    public void testUpdateUser() {
        // Create a new user
        UserRegistrationRequest user = new UserRegistrationRequest();
        user.setUsername("test_user");
        user.setFullName("Test User");
        user.setEmail("test.user@example.com");
        user.setPassword("testpassword");

        User savedUser = userService.registerUser(user);

        // Update user information
        savedUser.setFullName("Updated User");

        // Assume updateUser is the method to update an existing user
        User updatedUser = userService.updateUser(savedUser.getId(), savedUser);

        // Verify that the user has been updated correctly
        assertEquals("Updated User", updatedUser.getFullName());
    }

    @Test
    public void testDeleteUser() {
        UserRegistrationRequest user = new UserRegistrationRequest();
        user.setUsername("test_user");
        user.setFullName("Test User");
        user.setEmail("test.user@example.com");
        user.setPassword("testpassword");

        // 사용자 등록
        User savedUser = userService.registerUser(user);

        // 사용자 삭제
        userService.deleteUser(savedUser.getId());

        // 사용자가 삭제되었는지 확인 (예외 발생)
        assertThrows(ResponseStatusException.class, () -> userService.getUserById(savedUser.getId()));
    }

    @Test
    public void testDuplicateEmail() {
        UserRegistrationRequest user1 = new UserRegistrationRequest();
        user1.setUsername("test_user1");
        user1.setFullName("Test User1");
        user1.setEmail("test.user@example.com");
        user1.setPassword("testpassword");


        UserRegistrationRequest user2 = new UserRegistrationRequest();
        user2.setUsername("test_user2");
        user2.setFullName("Test User2");
        user2.setEmail("test.user@example.com");
        user2.setPassword("testpassword2");


        userService.registerUser(user1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(user2);
        });

        String expectedMessage = "Email is already in use";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}