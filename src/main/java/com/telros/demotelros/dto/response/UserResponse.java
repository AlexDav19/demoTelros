package com.telros.demotelros.dto.response;

import com.telros.demotelros.entity.User;

import java.time.LocalDate;

public record UserResponse(
        Long id,
        String lastName,
        String firstName,
        String surName,
        LocalDate birthday,
        String email,
        String telephone) {

    public UserResponse(User user) {
        this(user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getSurName(),
                user.getBirthday(),
                user.getEmail(),
                user.getTelephone()
        );
    }
}
