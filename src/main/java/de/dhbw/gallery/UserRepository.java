package de.dhbw.gallery;

import de.dhbw.gallery.domain.User;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;

/**
 * Abstract class of the DAO object to interact with the database
 *
 * This class is populated by Micronaut but you can add additional methods
 */
@JdbcRepository
abstract public class UserRepository implements CrudRepository<User, Long> {
}
