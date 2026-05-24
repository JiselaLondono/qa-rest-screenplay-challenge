package com.qa.challenge.runners;

import com.qa.challenge.models.request.UserRequest;
import com.qa.challenge.models.response.UserResponse;
import com.qa.challenge.questions.TheResponseBodyUser;
import com.qa.challenge.questions.TheStatusCodeResponse;
import com.qa.challenge.tasks.CreateUser;
import com.qa.challenge.tasks.DeleteUser;
import com.qa.challenge.tasks.GetUser;
import com.qa.challenge.tasks.UpdateUser;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import net.datafaker.Faker;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestCreationUser extends BaseTest {

    private static final Faker faker = new Faker();
    private static int userId;

    private static final UserRequest userRequest = UserRequest.builder()
            .name(faker.name().fullName())
            .email(faker.internet().emailAddress())
            .gender(faker.options().option("male", "female"))
            .status(faker.options().option("active", "inactive"))
            .build();

    private static final Matcher<UserResponse> matchesUserRequest(UserRequest request) {
        return allOf(
                notNullValue(),
                hasProperty("id", is(notNullValue())),
                hasProperty("name", is(equalTo(request.getName()))),
                hasProperty("email", is(equalTo(request.getEmail()))),
                hasProperty("gender", is(equalTo(request.getGender()))),
                hasProperty("status", is(equalTo(request.getStatus()))));
    }

    @Test
    @Order(1)
    void createUser() {

        actor.attemptsTo(CreateUser.withData(userRequest));

        UserResponse userResponse = actor.asksFor(TheResponseBodyUser.obtainedIs());
        userId = userResponse.getId();

        actor.should(
                seeThat(TheStatusCodeResponse.is(), equalTo(SC_CREATED)),
                seeThat("the created user details", TheResponseBodyUser.obtainedIs(), matchesUserRequest(userRequest)));
    }

    @Test
    @Order(2)
    void getUser() {

        actor.attemptsTo(GetUser.withData(userId));

        actor.should(
                seeThat(TheStatusCodeResponse.is(), equalTo(SC_OK)),
                seeThat("the user details", TheResponseBodyUser.obtainedIs(), matchesUserRequest(userRequest)),
                seeThat("the user id", TheResponseBodyUser.obtainedIs(), hasProperty("id", is(equalTo(userId)))));
    }

    @Test
    @Order(3)
    void updateUser() {

        UserRequest userRequestToUpdate = UserRequest.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option("male", "female"))
                .status(faker.options().option("active", "inactive"))
                .build();

        actor.attemptsTo(UpdateUser.withData(userRequestToUpdate, userId));

        actor.should(
                seeThat(TheStatusCodeResponse.is(), equalTo(SC_OK)),
                seeThat("the updated user details", TheResponseBodyUser.obtainedIs(),
                        matchesUserRequest(userRequestToUpdate)),
                seeThat("the user id", TheResponseBodyUser.obtainedIs(), hasProperty("id", is(equalTo(userId)))));
    }

    @Test
    @Order(4)
    void deleteUser() {

        actor.attemptsTo(DeleteUser.withData(userId));

        actor.should(
                seeThat(TheStatusCodeResponse.is(), equalTo(SC_NO_CONTENT)));
    }

}
