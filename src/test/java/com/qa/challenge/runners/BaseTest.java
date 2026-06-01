package com.qa.challenge.runners;

import com.qa.challenge.utils.EnviromentConfig;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class BaseTest {

    protected Actor actor;

    @BeforeEach
    public void setStage() {
        SerenityRest.useRelaxedHTTPSValidation();
        actor = Actor.named("Jisela").whoCan(CallAnApi.at(EnviromentConfig.getBaseUrl()));
        actor.remember("API_TOKEN", EnviromentConfig.getToken());
    }
}
