package com.telros.demotelros.dto.response;

import com.telros.demotelros.entity.User;

import java.time.LocalDate;

public record UserDetailedInfoResponse(String lastName,
                                       String firstName,
                                       String surName,
                                       LocalDate birthday) {

    public UserDetailedInfoResponse(User user) {
        this(user.getLastName(),
                user.getFirstName(),
                user.getSurName(),
                user.getBirthday());
    }
}
