package de.dhbw.gallery


import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class PhotoControllerTest extends Specification {

    public static final String photo_uri = "/photo"
    @Inject
    @Client('/')
    RxHttpClient client

    void testConnect() {
        when:
        String requestBody = "{\"name\": \"somename\", \"title\": \"sometitle\", \"owner\": {\"id\": \"17\", \"name\": \"somename\"}}";
        HttpRequest<String> request = HttpRequest.POST(photo_uri, requestBody);
        String result = client.toBlocking().retrieve(request);

        then:
        result == "{\"id\":1,\"name\":\"somename\",\"title\":\"sometitle\",\"owner\":{\"id\":17,\"name\":\"somename\"}}"
    }

    void testCreate() {
        when:
        String requestBody = "{\"name\": \"somename\", \"title\": \"sometitle\", \"owner\": {\"id\": \"17\", \"name\": \"somename\"}}";
        HttpRequest<String> createRequest = HttpRequest.POST(photo_uri, requestBody);
        String result = client.toBlocking().retrieve(createRequest);

        then:
        result == "{\"id\":2,\"name\":\"somename\",\"title\":\"sometitle\",\"owner\":{\"id\":17,\"name\":\"somename\"}}"

        cleanup:
        def request = HttpRequest.DELETE(photo_uri);
        client.toBlocking().exchange(request);
    }

    void testList() {
        def response = createPhotoJson(1)
        response = createPhotoJson(2)
        response = createPhotoJson(3)

        when:
        HttpRequest<String> createRequest = HttpRequest.GET(photo_uri);
        String result = client.toBlocking().retrieve(createRequest);
        then:
        result == "[{\"id\":3,\"name\":\"somename_1\",\"title\":\"sometitle\",\"owner\":{\"id\":17}},{\"id\":4,\"name\":\"somename_2\",\"title\":\"sometitle\",\"owner\":{\"id\":17}},{\"id\":5,\"name\":\"somename_3\",\"title\":\"sometitle\",\"owner\":{\"id\":17}}]"

        cleanup:
        def request = HttpRequest.DELETE(photo_uri)
        client.toBlocking().exchange(request)
    }

    private String createPhotoJson(int num) {
        String requestBody = "{\"name\": \"somename_${num}\", \"title\": \"sometitle\", \"owner\": {\"id\": \"17\", \"name\": \"user\"}}"
        HttpRequest<String> createRequest = HttpRequest.POST(photo_uri, requestBody)
        return client.toBlocking().retrieve(createRequest)
    }
}
