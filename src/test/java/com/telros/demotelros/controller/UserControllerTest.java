package com.telros.demotelros.controller;

import com.telros.demotelros.dto.request.UserRequest;
import com.telros.demotelros.dto.response.UserResponse;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getUserTest_success() {
        final long userId = 0;
        User user = new User("2","2","2", LocalDate.now(), "2","2");
        UserResponse userResponse = new UserResponse(user);

        Mockito.when(userService.getUserById(userId)).thenReturn(userResponse);

        ResponseEntity<UserResponse> expected = ResponseEntity.ok(userResponse);
        ResponseEntity<UserResponse> actual = userController.getUser(userId);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUserTest_fail() {
        final long userId = 0;
        User user = new User("2","2","2", LocalDate.now(), "2","2");
        UserResponse userResponse = new UserResponse(user);
        User userFail = new User("1","1","1", LocalDate.now(), "1","1");
        UserResponse userResponseFail = new UserResponse(userFail);
        Mockito.when(userService.getUserById(userId)).thenReturn(userResponse);

        ResponseEntity<UserResponse> expected = ResponseEntity.ok(userResponseFail);
        ResponseEntity<UserResponse> actual = userController.getUser(userId);

        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    void createUserTest_success() {
        User user = new User("2","2","2", LocalDate.now(), "2","2");
        UserRequest userRequest = new UserRequest(user.getLastName(),
                user.getFirstName(),
                user.getSurName(),
                user.getBirthday(),
                user.getEmail(),
                user.getTelephone());

        Mockito.when(userService.createUser(userRequest)).thenReturn(user);

        ResponseEntity<User> expected = ResponseEntity.ok(user);
        ResponseEntity<User> actual = userController.createUser(userRequest);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createUserTest_fail() {
        User user = new User("2","2","2", LocalDate.now(), "2","2");
        User userFail = new User("1","1","1", LocalDate.now(), "1","1");
        UserRequest userRequest = new UserRequest(user.getLastName(),
                user.getFirstName(),
                user.getSurName(),
                user.getBirthday(),
                user.getEmail(),
                user.getTelephone());

        Mockito.when(userService.createUser(userRequest)).thenReturn(user);

        ResponseEntity<User> expected = ResponseEntity.ok(userFail);
        ResponseEntity<User> actual = userController.createUser(userRequest);

        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    void updateUserTest_success() {
        final long userId = 0;
        User user = new User("2","2","2", LocalDate.now(), "2","2");
        UserRequest userRequest = new UserRequest(user.getLastName(),
                user.getFirstName(),
                user.getSurName(),
                user.getBirthday(),
                user.getEmail(),
                user.getTelephone());

        Mockito.when(userService.updateUser(userId, userRequest)).thenReturn(user);

        ResponseEntity<User> expected = ResponseEntity.ok(user);
        ResponseEntity<User> actual = userController.updateUser(userId, userRequest);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateUserTest_fail() {
        final long userId = 0;
        User user = new User("2","2","2", LocalDate.now(), "2","2");
        User userFail = new User("1","1","1", LocalDate.now(), "1","1");
        UserRequest userRequest = new UserRequest(user.getLastName(),
                user.getFirstName(),
                user.getSurName(),
                user.getBirthday(),
                user.getEmail(),
                user.getTelephone());

        Mockito.when(userService.updateUser(userId, userRequest)).thenReturn(user);

        ResponseEntity<User> expected = ResponseEntity.ok(userFail);
        ResponseEntity<User> actual = userController.updateUser(userId, userRequest);

        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    void deleteUserTest_success() {
        final long userId = 0;
        User user = new User("2","2","2", LocalDate.now(), "2","2");

        Mockito.when(userService.deleteUser(userId)).thenReturn(user);

        ResponseEntity<User> expected = ResponseEntity.ok(user);
        ResponseEntity<User> actual = userController.deleteUser(userId);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteUserTest_fail() {
        final long userId = 0;
        User user = new User("2","2","2", LocalDate.now(), "2","2");
        User userFail = new User("1","1","1", LocalDate.now(), "1","1");

        Mockito.when(userService.deleteUser(userId)).thenReturn(user);

        ResponseEntity<User> expected = ResponseEntity.ok(userFail);
        ResponseEntity<User> actual = userController.deleteUser(userId);

        Assertions.assertNotEquals(expected, actual);
    }
}