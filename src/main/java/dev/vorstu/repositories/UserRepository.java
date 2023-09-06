package dev.vorstu.repositories;

import dev.vorstu.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional <User> findByUsername(String username);
    Boolean existsByUsername (String username);
}
