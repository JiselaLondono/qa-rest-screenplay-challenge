package com.qa.challenge.runners;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class BaseTest {

    protected Actor actor;
    private String theRestApiBaseUrl;
    private EnvironmentVariables environmentVariables;

    @BeforeEach
    public void configureBaseUrl() {
        theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl")
                .orElse("https://gorest.co.in");

        SerenityRest.useRelaxedHTTPSValidation();

        actor = Actor.named("Jisela").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

}
