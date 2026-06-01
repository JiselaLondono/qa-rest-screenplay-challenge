package com.qa.challenge.runners;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.qa.challenge.models.request.UserRequest;
import com.qa.challenge.models.response.UserResponse;
import com.qa.challenge.questions.TheResponseBodyUser;
import com.qa.challenge.questions.TheStatusCodeResponse;
import com.qa.challenge.tasks.CreateUser;
import com.qa.challenge.tasks.DeleteUser;
import com.qa.challenge.tasks.GetUser;
import com.qa.challenge.tasks.UpdateUser;
import net.datafaker.Faker;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserSuite extends BaseTest {

    private static final Faker faker = new Faker();
    private static String userId;

    private static UserRequest generateRandomUserRequest() {
        return UserRequest.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option("male", "female"))
                .status(faker.options().option("active", "inactive"))
                .build();
    }

    private static final UserRequest userRequest = generateRandomUserRequest();

    private static Matcher<UserResponse> matchesUserRequest(
            UserRequest request, String expectedId) {
        return allOf(
                notNullValue(),
                hasProperty(
                        "id", expectedId != null ? is(equalTo(expectedId)) : is(notNullValue())),
                hasProperty("name", is(equalTo(request.getName()))),
                hasProperty("email", is(equalTo(request.getEmail()))),
                hasProperty("gender", is(equalTo(request.getGender()))),
                hasProperty("status", is(equalTo(request.getStatus()))));
    }

    @Test
    @Order(1)
    void createUser() {
        actor.attemptsTo(CreateUser.withData(userRequest));
        actor.should(
                seeThat(TheStatusCodeResponse.is(), equalTo(SC_CREATED)),
                seeThat(
                        "the created user details",
                        TheResponseBodyUser.obtainedIs(),
                        matchesUserRequest(userRequest, null)));

        UserResponse userResponse = actor.asksFor(TheResponseBodyUser.obtainedIs());
        userId = userResponse.getId();
    }

    @Test
    @Order(2)
    void getUser() {
        actor.attemptsTo(GetUser.withData(userId));
        actor.should(
                seeThat(TheStatusCodeResponse.is(), equalTo(SC_OK)),
                seeThat(
                        "the user details",
                        TheResponseBodyUser.obtainedIs(),
                        matchesUserRequest(userRequest, userId)));
    }

    @Test
    @Order(3)
    void updateUser() {
        UserRequest userRequestToUpdate = generateRandomUserRequest();
        actor.attemptsTo(UpdateUser.withData(userRequestToUpdate, userId));
        actor.should(
                seeThat(TheStatusCodeResponse.is(), equalTo(SC_OK)),
                seeThat(
                        "the updated user details",
                        TheResponseBodyUser.obtainedIs(),
                        matchesUserRequest(userRequestToUpdate, userId)));
    }

    @Test
    @Order(4)
    void deleteUser() {
        actor.attemptsTo(DeleteUser.withData(userId));
        actor.should(seeThat(TheStatusCodeResponse.is(), equalTo(SC_NO_CONTENT)));
    }
}
