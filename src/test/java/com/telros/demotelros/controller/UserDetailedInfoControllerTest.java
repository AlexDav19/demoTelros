package com.telros.demotelros.controller;

import com.telros.demotelros.dto.response.UserDetailedInfoResponse;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.service.UserDetailedInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class UserDetailedInfoControllerTest {
    @Mock
    private UserDetailedInfoService userDetailedInfoService;
    @InjectMocks
    UserDetailedInfoController userDetailedInfoController;

    @Test
    void getUser() {
        final long userId = 0;
        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");
        UserDetailedInfoResponse userResponse = new UserDetailedInfoResponse(user);

        Mockito.when(userDetailedInfoService.getUserById(userId)).thenReturn(userResponse);

        ResponseEntity<UserDetailedInfoResponse> expected = ResponseEntity.ok(userResponse);
        ResponseEntity<UserDetailedInfoResponse> actual = userDetailedInfoController.getUser(userId);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateUser() {
        final long userId = 0;
        final LocalDate birthday = LocalDate.now();

        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");
        UserDetailedInfoResponse userResponse = new UserDetailedInfoResponse(user);

        Mockito.when(userDetailedInfoService.updateUser(userId, birthday)).thenReturn(userResponse);

        ResponseEntity<UserDetailedInfoResponse> expected = ResponseEntity.ok(userResponse);
        ResponseEntity<UserDetailedInfoResponse> actual = userDetailedInfoController.updateUser(userId, birthday);

        Assertions.assertEquals(expected, actual);
    }
}