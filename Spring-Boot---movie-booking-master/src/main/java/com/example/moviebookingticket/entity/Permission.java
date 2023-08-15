package com.example.moviebookingticket.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN("ADMIN"),
    USER("USER")

    ;
    @Getter
    private final String permission;
}
