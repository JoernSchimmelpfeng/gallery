package de.dhbw.gallery

import de.dhbw.gallery.domain.User
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class UserControllerTest extends Specification {

    @Inject
    @Client('/')
    RxHttpClient client

    void testConnect() {
        when:
        String requestBody = "{\"id\": \"17\", \"name\": \"somename\"}";
        HttpRequest<String> request = HttpRequest.POST("/user", requestBody);
        String result = client.toBlocking().retrieve(request);

        then:
        result == "{\"id\":1,\"name\":\"somename\"}"
    }
}
