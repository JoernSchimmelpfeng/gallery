package de.dhbw.gallery;

import de.dhbw.gallery.domain.Photo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.validation.Valid;

@Singleton
@Controller("/photo")
public class PhotoController {
    private final PhotoRepository repository;
    private static final Logger log = LoggerFactory.getLogger(PhotoController.class);

    public PhotoController(PhotoRepository photoRepository) {
        this.repository = photoRepository;
    }

    @Get("/{id}")
    public Photo show(Long id) {
        log.debug("Get photo with id {}", id);
        return repository
                .findById(id)
                .orElse(null);
    }

    @Put("/{id}")
    public HttpResponse update(@Body @Valid Photo photo) {
        log.debug("Update photo: {}", photo);
        repository.update(photo);

        return HttpResponse
                .noContent();
    }

    @Get()
    public Iterable<Photo> list() {
        log.debug("List photos");

        return repository.findAll();
    }

    @Post("/")
    public HttpResponse<Photo> save(@Body @Valid Photo photo) {
        log.debug("Save photo: {}", photo);
        Photo newPhoto = repository.save(photo);

        return HttpResponse
                .created(newPhoto);
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        log.debug("Save photo: {}", id);
        repository.deleteById(id);
        return HttpResponse.noContent();
    }

    @Delete("/")
    public HttpResponse deleteAll() {
        log.debug("Deleting all photos");
        repository.deleteAll();
        return HttpResponse.noContent();
    }
}
