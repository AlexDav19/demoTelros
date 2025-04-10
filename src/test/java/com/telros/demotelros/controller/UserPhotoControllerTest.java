package com.telros.demotelros.controller;

import com.telros.demotelros.entity.Photo;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.service.UserPhotoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class UserPhotoControllerTest {
    @Mock
    private UserPhotoService userPhotoService;
    @InjectMocks
    private UserPhotoController userPhotoController;

    @Test
    void getUserPhoto() {
        final long userId = 0;
        byte[] data = {2};
        User user = new User("2", "2", "2", LocalDate.now(), "2", "2");
        Photo photo = new Photo(1, "", 1, "image/png", data, user);

        Mockito.when(userPhotoService.getUserPhotoById(userId)).thenReturn(photo);

        byte[] actual = userPhotoController.getUserPhoto(userId).getBody();

        Assertions.assertEquals(data, actual);
    }

    @Test
    void createUserPhoto() {
    }

    @Test
    void deleteUserPhoto() {
    }
}