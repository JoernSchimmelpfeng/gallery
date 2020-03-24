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

/**
 * Controller for the user REST API
 */
@Controller("/user")
@Singleton
public class UserController {
    private final UserRepository repository;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Get("/{id}")
    public User show(Long id) {
        log.debug("Get user with id {}", id);
        return repository
                .findById(id)
                .orElse(null);
    }

    @Put("/{id}")
    public HttpResponse update(@Body @Valid User user) {
        log.debug("Updating user {}", user);
        repository.update(user);

        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, "mylocation");
    }

    @Get()
    public Iterable<User> list() {
        log.debug("List all users");
        return repository.findAll();
    }

    @Post("/")
    public HttpResponse<User> save(@Body @Valid User user) {
        log.debug("Save user {}", user);
        User newUser = repository.save(user);

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
        log.debug("Deleting user {}", id);
        repository.deleteById(id);
        return HttpResponse.noContent();
    }

    @Delete("/")
    public HttpResponse deleteAll() {
        log.debug("Deleting all user");
        repository.deleteAll();
        return HttpResponse.noContent();
    }
}
