package com.qa.challenge.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Resources {

    CREATE_USER("/public/v2/users"),
    GET_USER("/public/v2/users/{id}"),
    UPDATE_USER("/public/v2/users/{id}"),
    DELETE_USER("/public/v2/users/{id}");

    private final String value;
}
