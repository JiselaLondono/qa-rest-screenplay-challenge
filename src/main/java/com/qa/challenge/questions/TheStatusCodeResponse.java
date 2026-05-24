package com.qa.challenge.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class TheStatusCodeResponse implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return lastResponse().getStatusCode();
    }

    public static TheStatusCodeResponse is() {
        return new TheStatusCodeResponse();
    }

}
