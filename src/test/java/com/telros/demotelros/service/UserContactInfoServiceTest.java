package com.telros.demotelros.service;

import com.telros.demotelros.dto.response.UserContactInfoResponse;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

class UserContactInfoServiceTest {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    UserContactInfoService userContactInfoService = new UserContactInfoService(userRepository);

    @Test
    void getUserById() {
        final long userId = 0;
        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");
        UserContactInfoResponse userResponse = new UserContactInfoResponse(user);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserContactInfoResponse actual = userContactInfoService.getUserById(userId);

        Assertions.assertEquals(userResponse, actual);
    }

    @Test
    void updateUser() {
        final long userId = 0;
        final String telephone = "telephone";
        final String email = "email";
        User user = new User("2", "2", "2", LocalDate.now(), "email", "telephone");
        UserContactInfoResponse userResponse = new UserContactInfoResponse(user);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserContactInfoResponse actual = userContactInfoService.updateUser(userId, telephone, email);

        Assertions.assertEquals(userResponse, actual);
    }
}