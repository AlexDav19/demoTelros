package com.telros.demotelros.controller;

import com.telros.demotelros.entity.Photo;
import com.telros.demotelros.service.UserPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user/photo")
public class UserPhotoController {
    @Autowired
    UserPhotoService userPhotoService;

    public UserPhotoController(UserPhotoService userPhotoService) {
        this.userPhotoService = userPhotoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение фото пользователя по id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully retrieved",
                            content = {
                                    @Content(
                                            mediaType = "application/json"
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Service error"),
            }, tags = "Фото пользователя"
    )
    public ResponseEntity<byte[]> getUserPhoto(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id) {
        Photo photo = userPhotoService.getUserPhotoById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(photo.getMediaType()));
        headers.setContentLength(photo.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(photo.getData());
    }

    @PostMapping(value = "/{id}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление/изменение нового фото пользователя",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully retrieved",
                            content = {
                                    @Content(
                                            mediaType = "application/json"
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Service error"),
            }, tags = "Фото пользователя"
    )
    public ResponseEntity<String> createUserPhoto(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id,
            @RequestBody MultipartFile photo) throws IOException {
        if (photo.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("Файл слишком большой");
        }

        userPhotoService.createUserPhoto(id, photo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление фото пользователя",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully retrieved",
                            content = {
                                    @Content(
                                            mediaType = "application/json"
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Service error"),
            }, tags = "Фото пользователя"
    )
    public ResponseEntity<Photo> deleteUserPhoto(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id) {
        userPhotoService.deleteUserPhoto(id);
        return ResponseEntity.ok().build();
    }
}
