package com.telros.demotelros.controller;

import com.telros.demotelros.dto.response.UserContactInfoResponse;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.service.UserContactInfoService;
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
class UserContactInfoControllerTest {
    @Mock
    private UserContactInfoService userContactInfoService;
    @InjectMocks
    UserContactInfoController userContactInfoController;

    @Test
    void getUser() {
        final long userId = 0;
        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");
        UserContactInfoResponse userResponse = new UserContactInfoResponse(user);

        Mockito.when(userContactInfoService.getUserById(userId)).thenReturn(userResponse);

        ResponseEntity<UserContactInfoResponse> expected = ResponseEntity.ok(userResponse);
        ResponseEntity<UserContactInfoResponse> actual = userContactInfoController.getUser(userId);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void updateUser() {
        final long userId = 0;
        final String telephone = "telephone";
        final String email = "email";

        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");
        UserContactInfoResponse userResponse = new UserContactInfoResponse(user);

        Mockito.when(userContactInfoService.updateUser(userId, telephone, email)).thenReturn(userResponse);

        ResponseEntity<UserContactInfoResponse> expected = ResponseEntity.ok(userResponse);
        ResponseEntity<UserContactInfoResponse> actual = userContactInfoController.updateUser(userId, telephone, email);

        Assertions.assertEquals(expected, actual);
    }
}