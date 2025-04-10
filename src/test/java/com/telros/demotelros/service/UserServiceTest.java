package com.telros.demotelros.service;

import com.telros.demotelros.dto.request.UserRequest;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

class UserServiceTest {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    UserService userService = new UserService(userRepository);

    @Test
    void getUserById_success() {
        final long userId = 0;
        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User actual = userService.getUserById(userId);

        Assertions.assertEquals(user, actual);
    }

    @Test
    void createUser_success() {
        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");
        UserRequest userRequest = new UserRequest(
                user.getLastName(),
                user.getFirstName(),
                user.getSurName(),
                user.getBirthday(),
                user.getEmail(),
                user.getTelephone()
        );

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User actual = userService.createUser(userRequest);

        Assertions.assertEquals(user, actual);
    }

    @Test
    void updateUser_success() {
        final long userId = 0;
        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");
        UserRequest userRequest = new UserRequest(
                user.getLastName(),
                user.getFirstName(),
                user.getSurName(),
                user.getBirthday(),
                user.getEmail(),
                user.getTelephone()
        );

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User actual = userService.updateUser(userId, userRequest);

        Assertions.assertEquals(user, actual);
    }

    @Test
    void deleteUser_success() {
        final long userId = 0;
        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User actual = userService.deleteUser(userId);

        Assertions.assertEquals(user, actual);
    }
}