package com.telros.demotelros.controller;

import com.telros.demotelros.dto.response.UserContactInfoResponse;
import com.telros.demotelros.service.UserContactInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/ContactInfo")
public class UserContactInfoController {
    @Autowired
    UserContactInfoService userContactInfoService;

    public UserContactInfoController(UserContactInfoService userContactInfoService) {
        this.userContactInfoService = userContactInfoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение контактной информации пользователя по id",
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
            }, tags = "Контактная информация пользователя"
    )
    public ResponseEntity<UserContactInfoResponse> getUser(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(userContactInfoService.getUserById(id));
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
            }, tags = "Контактная информация пользователя"
    )
    public ResponseEntity<UserContactInfoResponse> updateUser(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "телефон пользователя", example = "1")
            @RequestParam(required = false) String telephone,
            @Parameter(description = "email пользователя", example = "1")
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(userContactInfoService.updateUser(id, telephone, email));
    }
}
