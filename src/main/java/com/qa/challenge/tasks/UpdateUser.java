package com.qa.challenge.tasks;

import com.qa.challenge.interactions.Put;
import com.qa.challenge.models.request.UserRequest;
import com.qa.challenge.utils.Resources;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.apache.http.auth.AUTH;

public class UpdateUser implements Task {

    private final UserRequest userRequest;
    private final String id;

    public UpdateUser(UserRequest userRequest, String id) {
        this.userRequest = userRequest;
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(Resources.UPDATE_USER.getValue())
                        .with(
                                request ->
                                        request.contentType(ContentType.JSON)
                                                .pathParam("id", id)
                                                .header(
                                                        AUTH.WWW_AUTH_RESP,
                                                        "Bearer ".concat(actor.recall("API_TOKEN")))
                                                .body(userRequest)));
    }

    public static Performable withData(UserRequest userRequest, String id) {
        return Tasks.instrumented(UpdateUser.class, userRequest, id);
    }
}
