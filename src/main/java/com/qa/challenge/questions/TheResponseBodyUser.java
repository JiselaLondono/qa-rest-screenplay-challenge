package com.qa.challenge.questions;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.qa.challenge.models.response.UserResponse;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheResponseBodyUser implements Question<UserResponse> {

    @Override
    public UserResponse answeredBy(Actor actor) {
        return lastResponse().getBody().as(UserResponse.class);
    }

    public static TheResponseBodyUser obtainedIs() {
        return new TheResponseBodyUser();
    }

}
