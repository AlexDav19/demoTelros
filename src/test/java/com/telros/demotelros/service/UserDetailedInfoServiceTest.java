package com.telros.demotelros.service;

import com.telros.demotelros.dto.response.UserDetailedInfoResponse;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailedInfoServiceTest {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    UserDetailedInfoService userDetailedInfoService = new UserDetailedInfoService(userRepository);

    @Test
    void getUserById() {
        final long userId = 0;
        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");
        UserDetailedInfoResponse userResponse = new UserDetailedInfoResponse(user);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDetailedInfoResponse actual = userDetailedInfoService.getUserById(userId);

        Assertions.assertEquals(userResponse, actual);

    }

    @Test
    void updateUser() {
        final long userId = 0;
        final LocalDate birthday = LocalDate.now();

        User user = new User("2", "2", "2", birthday, "2", "2");
        UserDetailedInfoResponse userResponse = new UserDetailedInfoResponse(user);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDetailedInfoResponse actual = userDetailedInfoService.updateUser(userId, birthday);

        Assertions.assertEquals(userResponse, actual);
    }
}