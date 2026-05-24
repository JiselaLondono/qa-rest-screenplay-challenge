package com.qa.challenge.tasks;

import com.qa.challenge.models.request.UserRequest;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class UpdateUser implements Task {

    private final UserRequest userRequest;
    private final int id;

    public UpdateUser(UserRequest userRequest, int id) {
        this.userRequest = userRequest;
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Put.to("/public/v2/users/" + id)
                .with(request -> request.contentType(ContentType.JSON)
                        .header("Authorization",
                                "Bearer 703884be07bfe5c321450155ab4a1d71a72dabf83d93d7cba92461292242c66b")
                        .body(userRequest)));

    }

    public static Performable withData(UserRequest userRequest, int id) {
        return Tasks.instrumented(UpdateUser.class, userRequest, id);
    }

}
