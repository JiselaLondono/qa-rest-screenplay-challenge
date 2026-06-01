package com.qa.challenge.tasks;

import com.qa.challenge.interactions.Post;
import com.qa.challenge.models.request.UserRequest;
import com.qa.challenge.utils.Resources;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.apache.http.auth.AUTH;

public class CreateUser implements Task {

    private final UserRequest userRequest;

    public CreateUser(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Resources.CREATE_USER.getValue())
                        .with(
                                request ->
                                        request.contentType(ContentType.JSON)
                                                .header(
                                                        AUTH.WWW_AUTH_RESP,
                                                        "Bearer ".concat(actor.recall("API_TOKEN")))
                                                .body(userRequest)));
    }

    public static Performable withData(UserRequest userRequest) {
        return Tasks.instrumented(CreateUser.class, userRequest);
    }
}
