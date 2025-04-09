package com.telros.demotelros.dto.response;

import com.telros.demotelros.entity.User;

public record UserContactInfoResponse(String lastName,
                                      String firstName,
                                      String surName,
                                      String email,
                                      String telephone) {

    public UserContactInfoResponse(User user) {
        this(user.getLastName(),
                user.getFirstName(),
                user.getSurName(),
                user.getEmail(),
                user.getTelephone());
    }
}
