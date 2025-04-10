package com.telros.demotelros.controller;

import com.telros.demotelros.dto.request.UserRequest;
import com.telros.demotelros.dto.response.UserResponse;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по id",
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
            }, tags = "Пользователь"
    )
    public ResponseEntity<UserResponse> getUser(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    @Operation(summary = "Добавление нового пользователя",
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
            }, tags = "Пользователь"
    )
    public ResponseEntity<User> createUser(
            @RequestBody UserRequest user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение данных пользователя",
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
            }, tags = "Пользователь"
    )
    public ResponseEntity<User> updateUser(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id,
            @RequestBody UserRequest user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя",
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
            }, tags = "Пользователь"
    )
    public ResponseEntity<User> deleteUser(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id) {
        User user = userService.deleteUser(id);
        return ResponseEntity.ok(user);
    }
}
