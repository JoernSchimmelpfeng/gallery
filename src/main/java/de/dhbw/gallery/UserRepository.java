package de.dhbw.gallery;

import de.dhbw.gallery.domain.User;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository
public interface UserRepository extends CrudRepository<User, Long> {
}
