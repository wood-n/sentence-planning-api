package uk.gov.digital.justice.hmpps.sentenceplan.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test,disableauthorisation")
public class PingEndpointIntTest {

    @LocalServerPort
    int port;

    @Before
    public void setup() {
        RestAssured.port = port;

    }

    @Test
    public void healthEndpointIsUnauthorised() {
        var reponseBody = given()
                .when()
                .get("/ping")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.TEXT)
                .extract()
                .asString();

        assertThat(reponseBody).isEqualTo("pong");

    }
}
