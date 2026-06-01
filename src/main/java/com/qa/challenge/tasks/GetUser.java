package com.qa.challenge.tasks;

import com.qa.challenge.interactions.Get;
import com.qa.challenge.utils.Resources;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.apache.http.auth.AUTH;

public class GetUser implements Task {

    private final String id;

    public GetUser(String id) {
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.to(Resources.GET_USER.getValue())
                        .with(
                                request ->
                                        request.contentType(ContentType.JSON)
                                                .pathParam("id", id)
                                                .header(
                                                        AUTH.WWW_AUTH_RESP,
                                                        "Bearer "
                                                                .concat(
                                                                        actor.recall(
                                                                                "API_TOKEN")))));
    }

    public static Performable withData(String id) {
        return Tasks.instrumented(GetUser.class, id);
    }
}
