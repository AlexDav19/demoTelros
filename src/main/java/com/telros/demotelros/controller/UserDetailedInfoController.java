package com.telros.demotelros.controller;

import com.telros.demotelros.dto.response.UserDetailedInfoResponse;
import com.telros.demotelros.service.UserDetailedInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/user/DetailedInfo")
public class UserDetailedInfoController {
    @Autowired
    UserDetailedInfoService userDetailedInfoService;

    public UserDetailedInfoController(UserDetailedInfoService userDetailedInfoService) {
        this.userDetailedInfoService = userDetailedInfoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение детальной информации пользователя по id",
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
            }, tags = "Детальная информация пользователя"
    )
    public ResponseEntity<UserDetailedInfoResponse> getUser(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(userDetailedInfoService.getUserById(id));
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
            }, tags = "Детальная информация пользователя"
    )
    public ResponseEntity<UserDetailedInfoResponse> updateUser(
            @Parameter(description = "id пользователя", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "дата рождения пользователя", example = "2000-01-01", required = true)
            @RequestParam LocalDate birthday) {
        return ResponseEntity.ok(userDetailedInfoService.updateUser(id, birthday));
    }
}
