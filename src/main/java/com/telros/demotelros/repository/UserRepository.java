package com.telros.demotelros.repository;

import com.telros.demotelros.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserById(Long id);
}
