package com.qa.challenge.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnviromentConfig {

    private static final EnvironmentVariables environmentVariables =
            SystemEnvironmentVariables.createEnvironmentVariables();

    public static String getBaseUrl() {
        return EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("restapi.baseurl");
    }

    public static String getToken() {
        return EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("restapi.token");
    }
}
