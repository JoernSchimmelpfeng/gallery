package de.dhbw.gallery;


import de.dhbw.gallery.domain.Photo;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository
abstract public class PhotoRepository implements CrudRepository<Photo, Long>  {
}
