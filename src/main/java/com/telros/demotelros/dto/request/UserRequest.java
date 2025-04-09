package com.telros.demotelros.dto.request;

import java.time.LocalDate;

public record UserRequest(String lastName,
                        String firstName,
                        String surName,
                        LocalDate birthday,
                        String email,
                        String telephone) {
}