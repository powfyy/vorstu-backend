package dev.vorstu.repositories;

import dev.vorstu.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional <User> findByUsername(String username);
    Boolean existsByUsername (String username);
}
