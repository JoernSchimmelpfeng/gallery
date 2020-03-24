package de.dhbw.gallery

import de.dhbw.gallery.domain.User
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject


@MicronautTest
/**
 * This class creates a UserController that can be connected with a HTTP client.
 * The client can submit REST calls which can then be validated.
 */
class UserControllerTest extends Specification {

    @Inject
    @Client('/')
    RxHttpClient client

    void testCreate() {
        when:
        String requestBody = "{\"id\": \"17\", \"name\": \"somename\"}";
        HttpRequest<String> createRequest = HttpRequest.POST("/user", requestBody);
        String result = client.toBlocking().retrieve(createRequest);

        then:
        result == "{\"id\":1,\"name\":\"somename\"}"

        cleanup:
        def request = HttpRequest.DELETE("/user");
        client.toBlocking().exchange(request);
    }

    void testGetAll() {
        when:
        String requestBody = "{\"name\": \"somename1\"}";
        HttpRequest<String> createRequest = HttpRequest.POST("/user", requestBody);
        client.toBlocking().retrieve(createRequest);
        requestBody = "{\"name\": \"somename2\"}";
        createRequest = HttpRequest.POST("/user", requestBody);
        client.toBlocking().retrieve(createRequest);
        HttpRequest<User> request = HttpRequest.GET("/user");
        String result = client.toBlocking().retrieve(request);

        then:
        result == "[{\"id\":2,\"name\":\"somename1\"},{\"id\":3,\"name\":\"somename2\"}]"

        cleanup:
        request = HttpRequest.DELETE("/user");
        client.toBlocking().exchange(request);
    }


    void testDelete() {
        when:
        String requestBody = "{\"name\": \"somename1\"}";
        HttpRequest<String> createRequest = HttpRequest.POST("/user", requestBody);
        client.toBlocking().retrieve(createRequest);
        requestBody = "{\"name\": \"somename2\"}";
        createRequest = HttpRequest.POST("/user", requestBody);
        client.toBlocking().retrieve(createRequest);
        HttpRequest<String> request = HttpRequest.GET("/user");
        String result = client.toBlocking().retrieve(request);

        then:
        result == "[{\"id\":4,\"name\":\"somename1\"},{\"id\":5,\"name\":\"somename2\"}]"

        when:
        request = HttpRequest.DELETE("/user/4");
        def response = client.toBlocking().exchange(request);

        request = HttpRequest.GET("/user");
        result = client.toBlocking().retrieve(request);

        then:
        response.getStatus().getCode() == 204
        result == "[{\"id\":5,\"name\":\"somename2\"}]"
    }
}
