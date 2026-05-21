package com.qa.challenge.runners;

import com.qa.challenge.models.request.UserRequest;
import com.qa.challenge.questions.LastResponseStatusCode;
import com.qa.challenge.tasks.CreateUser;
import static org.apache.http.HttpStatus.SC_CREATED;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import net.datafaker.Faker;

class TestCreationUser extends BaseTest {

    private static final Faker faker = new Faker();

    @Test
    void createUser() {

        actor.attemptsTo(CreateUser.withData(UserRequest.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option("male", "female"))
                .status(faker.options().option("active", "inactive"))
                .build()));

        actor.should(seeThat(LastResponseStatusCode.is(), equalTo(SC_CREATED)));

    }

}
