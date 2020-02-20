package de.dhbw.gallery;

import de.dhbw.gallery.domain.User;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@Controller("/user")
@Singleton
public class UserController {
    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get("/{id}")
    public User show(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }

    @Put("/{id}")
    public HttpResponse update(@Body @Valid User user) {
        userRepository.update(user);

        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, "mylocation");
    }

    @Get()
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    @Post("/")
    public HttpResponse<User> save(@Body @Valid User user) {
        log.debug("User: {}", user);
        User newUser = userRepository.save(user);

        return HttpResponse
                .created(newUser)
                .headers(headers -> headers.location(getSomelocation()));
    }

    private URI getSomelocation() {
        try {
            return new URI("/somelocation");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        userRepository.deleteById(id);
        return HttpResponse.noContent();
    }

}