package dev.vorstu.repositories;

import dev.vorstu.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
