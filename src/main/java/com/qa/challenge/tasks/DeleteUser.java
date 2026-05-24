package com.qa.challenge.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteUser implements Task {

    private final int id;

    public DeleteUser(int id) {
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Delete.from("/public/v2/users/" + id).with(request -> request.contentType(ContentType.JSON)
                .header("Authorization",
                        "Bearer 703884be07bfe5c321450155ab4a1d71a72dabf83d93d7cba92461292242c66b")));

    }

    public static Performable withData(int id) {
        return Tasks.instrumented(DeleteUser.class, id);
    }

}
