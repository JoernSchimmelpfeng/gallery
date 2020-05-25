package de.dhbw.gallery;

import de.dhbw.gallery.domain.User;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.repository.CrudRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Abstract class of the DAO object to interact with the database
 *
 * This class is populated by Micronaut but you can add additional methods
 */
@JdbcRepository
abstract public class UserRepository implements CrudRepository<User, Long> {

    @Inject
    protected JdbcOperations jdbcOperations;

    @Override
    @Transactional
    public void deleteAll() {
        jdbcOperations.prepareStatement("delete from user", statement -> {
            return statement.executeUpdate();}
            );
    }
}
